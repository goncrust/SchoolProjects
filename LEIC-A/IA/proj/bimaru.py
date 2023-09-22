# bimaru.py: Template para implementação do projeto de Inteligência Artificial 2022/2023.
# Devem alterar as classes e funções neste ficheiro de acordo com as instruções do enunciado.
# Além das funções e classes já definidas, podem acrescentar outras que considerem pertinentes.

# Grupo 93:
# 102604 Gonçalo Rua
# 102611 João Gouveia

import sys
import copy
from search import Problem, depth_first_tree_search


class BimaruState:
    state_id = 0

    def __init__(self, board):
        self.board = board
        self.id = BimaruState.state_id
        BimaruState.state_id += 1

    def __lt__(self, other):
        return self.id < other.id

    def __str__(self) -> str:
        tiles = copy.deepcopy(self.board.tiles)
        for hint in self.board.hints:
            tiles[hint[0]][hint[1]] = hint[2].upper()

        return "\n".join(map("".join, tiles))


class Board:
    """Representação interna de um tabuleiro de Bimaru."""

    def __init__(self, cols, rows, tiles, hints, ships):
        self.cols = cols
        self.rows = rows
        self.tiles = tiles
        self.hints = hints
        self.ships = ships
        self.fill_blocked()

    def fill_row(self, row: int):
        for j in range(10):
            if self.tiles[row][j] == "0":
                self.tiles[row][j] = "."

    def fill_col(self, col: int):
        for i in range(10):
            if self.tiles[i][col] == "0":
                self.tiles[i][col] = "."

    def fill_blocked(self):
        for i in range(10):
            if self.cols[i] == 0:
                self.fill_col(i)

            if self.rows[i] == 0:
                self.fill_row(i)

    @staticmethod
    def parse_instance():
        """Lê o test do standard input (stdin) que é passado como argumento
        e retorna uma instância da classe Board.

        Por exemplo:
            $ python3 bimaru.py < input_T01

            > from sys import stdin
            > line = stdin.readline().split()
        """
        lines = sys.stdin.readlines()
        rows = list(map(int, lines[0].split()[1:]))
        cols = list(map(int, lines[1].split()[1:]))
        tiles = [["0"] * 10 for i in range(10)]
        hints = []
        for line in lines[3:]:
            components = line.split()[1:]
            new_tuple = (int(components[0]), int(components[1]), components[2].lower())
            hints.append(new_tuple)

        ships = [1, 1, 1, 1, 2, 2, 2, 3, 3, 4]
        return Board(cols, rows, tiles, hints, ships)


class Bimaru(Problem):
    def __init__(self, board: Board):
        """O construtor especifica o estado inicial."""
        super().__init__(BimaruState(board))

    def get_combinations(self, lst, size):
        if size == 0 or len(lst) < size:
            return []

        combinations = []
        self.generate_combinations(lst, size, 0, [], combinations)
        return combinations

    def generate_combinations(
        self, lst, size, start, current_combination, combinations
    ):
        if size == 0:
            combinations.append(current_combination.copy())
            return

        for i in range(start, len(lst)):
            current_combination.append(lst[i])
            self.generate_combinations(
                lst, size - 1, i + 1, current_combination, combinations
            )
            current_combination.pop()

    def overlap(self, ship1, ship2) -> bool:
        ship1_area = (
            (max(ship1[0][0] - 1, 0), max(ship1[0][1] - 1, 0)),
            (min(ship1[1][0] + 1, 9), min(ship1[1][1] + 1, 9)),
        )

        ship2_area = ((ship2[0][0], ship2[0][1]), (ship2[1][0], ship2[1][1]))

        if ship1_area[1][0] < ship2_area[0][0] or ship1_area[0][0] > ship2_area[1][0]:
            return False
        if ship1_area[1][1] < ship2_area[0][1] or ship1_area[0][1] > ship2_area[1][1]:
            return False

        return True

    def remove_incompatible(self, state, actions):
        possible_actions = []
        for action in actions:
            compatible = True

            action_size = len(action)
            for i in range(action_size - 1):
                for j in range(i + 1, action_size):
                    if self.overlap(action[i], action[j]):
                        compatible = False
                        break
                if not compatible:
                    break

            if not compatible:
                continue

            rows = state.board.rows.copy()
            cols = state.board.cols.copy()
            for ship in action:
                start = ship[0]
                end = ship[1]
                if start[0] == end[0] and start[1] == end[1]:
                    rows[start[0]] -= 1
                    cols[start[1]] -= 1
                elif start[0] == end[0]:
                    rows[start[0]] -= end[1] - start[1] + 1
                    for i in range(start[1], end[1] + 1):
                        cols[i] -= 1
                elif start[1] == end[1]:
                    cols[start[1]] -= end[0] - start[0] + 1
                    for i in range(start[0], end[0] + 1):
                        rows[i] -= 1

            for i in range(10):
                if rows[i] < 0 or cols[i] < 0:
                    compatible = False
                    break

            if not compatible:
                continue

            possible_actions.append(action)

        return possible_actions

    def actions(self, state: BimaruState):
        """Retorna uma lista de ações que podem ser executadas a
        partir do estado passado como argumento."""
        actions = []

        board = state.board
        tiles = board.tiles
        ships = board.ships
        if not ships:
            return []

        target_size = max(ships)
        ship_amount = ships.count(target_size)

        for hint in state.board.hints:
            if hint[2] == "w":
                if tiles[hint[0]][hint[1]] != "." and tiles[hint[0]][hint[1]] != "0":
                    return []
            elif tiles[hint[0]][hint[1]] != hint[2] and tiles[hint[0]][hint[1]] != "0":
                return []

        # linhas
        for i in range(10):
            for j in range(10):
                if tiles[i][j] == "0":
                    k = j
                    size = 1
                    while k < 10 and tiles[i][k] == "0" and size <= target_size:
                        not_wanted = [
                            (i, k, "w"),
                            (i, k, "c"),
                            (i, k, "t"),
                            (i, k, "b"),
                        ]
                        if (i, k, tiles[i][k]) in not_wanted:
                            break
                        if size <= board.rows[i] and size == target_size:
                            actions.append(((i, j), (i, k)))
                        size += 1
                        k += 1

        # colunas
        if target_size != 1:
            for i in range(10):
                for j in range(10):
                    if tiles[j][i] == "0":
                        k = j
                        size = 1
                        while k < 10 and tiles[k][i] == "0" and size <= target_size:
                            not_wanted = [
                                (i, k, "w"),
                                (i, k, "c"),
                                (i, k, "l"),
                                (i, k, "r"),
                            ]
                            if (k, i, tiles[k][i]) in not_wanted:
                                break
                            if size <= board.cols[i] and size == target_size:
                                actions.append(((j, i), (k, i)))
                            size += 1
                            k += 1

        combinations = self.get_combinations(actions, ship_amount)
        useful_actions = self.remove_incompatible(state, combinations)

        return useful_actions

    def fill_ship_area(self, tiles, action):
        start = action[0]
        end = action[1]

        min_col = max(start[1] - 1, 0)
        min_row = max(start[0] - 1, 0)
        max_col = min(end[1] + 1, 9)
        max_row = min(end[0] + 1, 9)

        for i in range(min_row, max_row + 1):
            for j in range(min_col, max_col + 1):
                tiles[i][j] = "."

    def result(self, state: BimaruState, action):
        """Retorna o estado resultante de executar a 'action' sobre
        'state' passado como argumento. A ação a executar deve ser uma
        das presentes na lista obtida pela execução de
        self.actions(state)."""
        board = state.board

        tiles = copy.deepcopy(board.tiles)
        rows = board.rows.copy()
        cols = board.cols.copy()
        ships = board.ships.copy()

        for ship in action:
            start = ship[0]
            end = ship[1]
            self.fill_ship_area(tiles, ship)

            if start[0] == end[0] and start[1] == end[1]:
                tiles[start[0]][start[1]] = "c"
                rows[start[0]] -= 1
                cols[start[1]] -= 1
                ships.remove(1)
            elif start[0] == end[0]:
                tiles[start[0]][start[1]] = "l"
                for j in range(start[1] + 1, end[1]):
                    tiles[start[0]][j] = "m"
                tiles[end[0]][end[1]] = "r"

                rows[start[0]] -= end[1] - start[1] + 1
                for i in range(start[1], end[1] + 1):
                    cols[i] -= 1
                ships.remove(end[1] - start[1] + 1)
            elif start[1] == end[1]:
                tiles[start[0]][start[1]] = "t"
                for i in range(start[0] + 1, end[0]):
                    tiles[i][start[1]] = "m"
                tiles[end[0]][end[1]] = "b"

                for i in range(start[0], end[0] + 1):
                    rows[i] -= 1
                cols[start[1]] -= end[0] - start[0] + 1
                ships.remove(end[0] - start[0] + 1)

        new_board = Board(cols, rows, tiles, board.hints, ships)
        return BimaruState(new_board)

    def goal_test(self, state: BimaruState):
        """Retorna True se e só se o estado passado como argumento é
        um estado objetivo. Deve verificar se todas as posições do tabuleiro
        estão preenchidas de acordo com as regras do problema."""
        board = state.board
        tiles = board.tiles
        hints = board.hints

        for i in range(10):
            if state.board.rows[i] != 0 or state.board.cols[i] != 0:
                return False

        for hint in hints:
            if hint[2] == "w":
                if tiles[hint[0]][hint[1]] != "." and tiles[hint[0]][hint[1]] != "0":
                    return False
            elif tiles[hint[0]][hint[1]] != hint[2]:
                return False

        return not board.ships


if __name__ == "__main__":
    input_board = Board.parse_instance()
    problem = Bimaru(input_board)
    solution = depth_first_tree_search(problem)
    if solution is not None:
        print(solution.state)
