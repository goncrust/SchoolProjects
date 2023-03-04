/*****************************************************************
 * IST: ASA 2022/23 - P2 - Pedro T. Monteiro | graph generator   *
 *****************************************************************
 * Random points between (0,0)-(M,M) -> Delaunay -> Graph        *
 * Using: https://github.com/delfrrr/delaunator-cpp              *
 *****************************************************************/
#include "delaunator.hpp"
#include <iostream>
#include <vector>
#include <map>

using namespace std;

unsigned int _V, _M, _currV;
int randLimit;
map<string, unsigned int> edgePool;

//-------------------------------------------------------------------
void printUsage(char *progname) {
  cout << "Usage: " << progname << " #V M p seed" << endl;
  cout << "\t#V: total number of vertices" << endl;
  cout << "\tM: max (M,M) coordinate where M >= V" << endl;
  cout << "\tp: probability to create new graph" << endl;
  cout << "\tseed: random seed number (optional)" << endl;
  exit(0);
}

void parseArgs(int argc, char **argv) {
  int seed = 0;

  if (argc < 4 || argc > 5) {
    cout << "ERROR: invalid # arguments" << endl;
    printUsage(argv[0]);
  }

  sscanf(argv[1], "%d", &_V);
  if (_V < 3) {
    cout << "ERROR: #V vertices must be >= 3" << endl;
    printUsage(argv[0]);
  }

  sscanf(argv[2], "%d", &_M);
  if (_M < _V) {
    cout << "ERROR: (M,M) coordinates must be >= V" << endl;
    printUsage(argv[0]);
  }

	float _p;
  sscanf(argv[3], "%f", &_p);
  if (_p < 0 || _p >= 1) {
    cout << "ERROR: probability p must be between [0, 1[" << endl;
    printUsage(argv[0]);
  }
	randLimit = RAND_MAX * _p;

  if (argc == 4) { // Init rand seed
    sscanf(argv[3], "%d", &seed);
    srand(seed);
  } else { // pseudo-rand seed
    srand((unsigned int)time(NULL));
  }
}

//-------------------------------------------------------------------
inline int randomValue(int max) {
  return rand() % max; // [0, max - 1]
}

inline void addEdge(map<pair<unsigned int, unsigned int>, unsigned int> &vertexPool, 
			int x0, int y0, int x1, int y1) {
	map<pair<unsigned int, unsigned int>, unsigned int>::iterator it0 = 
	 vertexPool.find({x0, y0});
	map<pair<unsigned int, unsigned int>, unsigned int>::iterator it1 = 
	 vertexPool.find({x1, y1});
	if (it1->second < it0->second)
		swap(it0, it1); // avoid repeated edges

	string key = to_string(it0->second) + " " + to_string(it1->second);
	if (edgePool.find(key) != edgePool.end())
		return; // (Reversed?) edge already inserted
	
	// Compute distance/weight
	unsigned int d = sqrt(pow((x0-x1),2) + pow((y0-y1),2));
	edgePool.insert({key, d});
}


//-------------------------------------------------------------------
void generateGraph() {
	// actual coordinates for Delaunay triangulation
	vector<double> coords;

	// to avoid repeated coordinates
  map<pair<unsigned int, unsigned int>, unsigned int> vertexPool;

  // Create a pool of points on a (0,0) x (_M, _M) square
	for (int i = 0; _currV < _V; i++) {
		pair<unsigned int, unsigned int> point = {randomValue(_M - 1), randomValue(_M - 1)};
		int id = ++_currV;
		vertexPool.insert({point, id});
		coords.push_back(point.first);
		coords.push_back(point.second);
		if (i > 3 && (_V - _currV > 3) && rand() < randLimit) 
			break; // ensures enough vertices for a triangle
  }

	// triangulation happens here
	delaunator::Delaunator d(coords);

	// Extract edges from triangles (may be repeated)
	for(size_t i = 0; i < d.triangles.size(); i += 3) {
		int px0 = d.coords[2 * d.triangles[i]];
		int py0 = d.coords[2 * d.triangles[i]+1];
		//--
		int px1 = d.coords[2 * d.triangles[i+1]];
		int py1 = d.coords[2 * d.triangles[i+1]+1];
		//--
		int px2 = d.coords[2 * d.triangles[i+2]];
		int py2 = d.coords[2 * d.triangles[i+2]+1];
		//--
		addEdge(vertexPool, px0, py0, px1, py1);
		addEdge(vertexPool, px1, py1, px2, py2);
		addEdge(vertexPool, px2, py2, px0, py0);
  }
}

//-------------------------------------------------------------------
int main(int argc, char *argv[]) {
  parseArgs(argc, argv);
	_currV = 0;

	// Possibility to generate disconnected graph
	while (_currV < _V) {
		generateGraph();
	}

	// print (set of) graph(s)
	cout << _V << endl << edgePool.size() << endl;
	for (auto const& elem : edgePool) {
		cout << elem.first << " " << elem.second << endl;
	}
  return 0;
}
