// Database
const GROUP_NUMBER = 40;
const RECORD_TO_FIREBASE = true; // Set to 'true' to record user results to Firebase

// Pixel density and setup variables (DO NOT CHANGE!)
let PPI, PPCM;
const NUM_OF_TRIALS = 12; // The numbers of trials (i.e., target selections) to be completed
const GRID_ROWS = 7; // We divide our targets in a 7x12 grid
const GRID_COLUMNS = 12;
const LETTERS_COLUMNS = 6; // Nested grid
const LETTERS_ROWS = 6;
const MAX_ROW_LENGTH = 5;
let continue_button;
let legendas; // The item list from the "legendas" CSV
let legendasObj; // Name column from the table, divided by letter

// Metrics
let testStartTime, testEndTime; // time between the start and end of one attempt (8 trials)
let hits = 0; // number of successful selections
let misses = 0; // number of missed selections (used to calculate accuracy)
let database; // Firebase DB

// Study control parameters
let draw_targets = false; // used to control what to show in draw()
let trials; // contains the order of targets that activate in the test
let current_trial = 0; // the current trial number (indexes into trials array above)
let attempt = 0; // users complete each test twice to account for practice (attemps 0 and 1)

// Letters
const letters = [
  "0",
  "A",
  "B",
  "C",
  "D",
  "E",
  "F",
  "G",
  "H",
  "I",
  "J",
  "K",
  "L",
  "M",
  "N",
  "O",
  "P",
  "Q",
  "R",
  "S",
  "T",
  "U",
  "V",
  "W",
  "X",
  "Y",
  "Z",
];

// Target list
let targets = {};

// State
let openLetter = "";

function close_letter() {
  openLetter = "";
}

function open_letter(letter) {
  openLetter = letter;
}

function is_open(letter) {
  return letter == openLetter;
}

// Ensures important data is loaded before the program starts
function preload() {
  legendas = loadTable("legendas.csv", "csv", "header");
}

function startWarning() {
  // Print warning at the start menu
  rect(200, 270, 300, 30);
  describe(
    "white rect with black outline and round edges in mid-right of canvas"
  );
  textFont("Arial", 26);
  fill(color(0, 0, 0));
  textAlign(CENTER);
  text("Display Size is to be 24.5", width / 2, 295);
}

// Runs once at the start
function setup() {
  const legendasColumn = legendas.getColumn("name").sort();
  legendasObj = {};
  for (const letter of letters) legendasObj[letter] = [];
  for (const legenda of legendasColumn) legendasObj[legenda[0]].push(legenda);

  close_letter();

  createCanvas(700, 500); // window size in px before we go into fullScreen()
  frameRate(60); // frame rate (DO NOT CHANGE!)

  randomizeTrials(); // randomize the trial order at the start of execution
  drawUserIDScreen(); // draws the user start-up screen (student ID and display size)
  startWarning();
}

// Runs every frame and redraws the screen
function draw() {
  if (draw_targets && attempt < 2) {
    // The user is interacting with the 6x3 target grid
    background(color(0, 0, 0)); // sets background to black

    // Print trial count at the top left-corner of the canvas
    textFont("Arial", 32);
    fill(color(255, 255, 255));
    textAlign(CENTER);
    text(
      "Trial " + (current_trial + 1) + " of " + trials.length,
      width / 2,
      30
    );

    // Draw all targets, traversing the target object
    if (openLetter != "") {
      targets[openLetter].backArrow.draw();
      for (const word in targets[openLetter].children) {
        targets[openLetter].children[word].draw();
      }
    } else {
      for (const letter in targets) {
        targets[letter].target.draw();
      }
    }

    // Draw the target label to be selected in the current trial
    fill(color(0, 0, 0));
    rect(0, height - 40, width, 40); // draws a black rectangle behind the trial label for optimal contrast

    textFont("Arial", 20);
    fill(color(255, 255, 255));
    textAlign(CENTER);
    text(legendas.getString(trials[current_trial], 0), width / 2, height - 20);
  }
}

// Print and save results at the end of 54 trials
function printAndSavePerformance() {
  // DO NOT CHANGE THESE!
  let accuracy = parseFloat(hits * 100) / parseFloat(hits + misses);
  let test_time = (testEndTime - testStartTime) / 1000;
  let time_per_target = nf(test_time / parseFloat(hits + misses), 0, 3);
  let penalty = constrain(
    (parseFloat(95) - parseFloat(hits * 100) / parseFloat(hits + misses)) * 0.2,
    0,
    100
  );
  let target_w_penalty = nf(
    test_time / parseFloat(hits + misses) + penalty,
    0,
    3
  );
  let timestamp =
    day() +
    "/" +
    month() +
    "/" +
    year() +
    "  " +
    hour() +
    ":" +
    minute() +
    ":" +
    second();

  textFont("Arial", 18);
  background(color(0, 0, 0)); // clears screen
  fill(color(255, 255, 255)); // set text fill color to white
  textAlign(LEFT);
  text(timestamp, 10, 20); // display time on screen (top-left corner)

  textAlign(CENTER);
  text("Attempt " + (attempt + 1) + " out of 2 completed!", width / 2, 60);
  text("Hits: " + hits, width / 2, 100);
  text("Misses: " + misses, width / 2, 120);
  text("Accuracy: " + accuracy + "%", width / 2, 140);
  text("Total time taken: " + test_time + "s", width / 2, 160);
  text("Average time per target: " + time_per_target + "s", width / 2, 180);
  text(
    "Average time for each target (+ penalty): " + target_w_penalty + "s",
    width / 2,
    220
  );

  // Saves results (DO NOT CHANGE!)
  let attempt_data = {
    project_from: GROUP_NUMBER,
    assessed_by: student_ID,
    test_completed_by: timestamp,
    attempt: attempt,
    hits: hits,
    misses: misses,
    accuracy: accuracy,
    attempt_duration: test_time,
    time_per_target: time_per_target,
    target_w_penalty: target_w_penalty,
  };

  // Send data to DB (DO NOT CHANGE!)
  if (RECORD_TO_FIREBASE) {
    // Access the Firebase DB
    if (attempt === 0) {
      firebase.initializeApp(firebaseConfig);
      database = firebase.database();
    }

    // Add user performance results
    let db_ref = database.ref("G" + GROUP_NUMBER);
    db_ref.push(attempt_data);
  }
}

// Mouse button was pressed - lets test to see if hit was in the correct target
function mousePressed() {
  // Only look for mouse releases during the actual test
  // (i.e., during target selections)
  if (draw_targets) {
    // Check if the user clicked over one of the targets
    if (openLetter != "") {
      if (targets[openLetter].backArrow.clicked(mouseX, mouseY)) close_letter();
      else {
        for (const word in targets[openLetter].children) {
          if (targets[openLetter].children[word].clicked(mouseX, mouseY)) {
            if (
              legendas.findRow(word, "name").getNum("id") ===
              trials[current_trial]
            ) {
              hits++;
            } else {
              misses++;
            }

            current_trial++;
            close_letter();
            break;
          }
        }
      }
    } else {
      for (const letter in targets) {
        if (targets[letter].target.clicked(mouseX, mouseY)) {
          open_letter(letter);
          break;
        }
      }
    }

    // Check if the user has completed all trials
    if (current_trial === NUM_OF_TRIALS) {
      testEndTime = millis();
      draw_targets = false; // Stop showing targets and the user performance results
      printAndSavePerformance(); // Print the user's results on-screen and send these to the DB
      attempt++;

      // If there's an attempt to go create a button to start this
      if (attempt < 2) {
        continue_button = createButton("START 2ND ATTEMPT");
        continue_button.mouseReleased(continueTest);
        continue_button.position(
          width / 2 - continue_button.size().width / 2,
          height / 2 - continue_button.size().height / 2
        );
      }
    }
    // Check if this was the first selection in an attempt
    else if (current_trial === 1) testStartTime = millis();
  }
}

// Evoked after the user starts its second (and last) attempt
function continueTest() {
  close_letter();

  // Re-randomize the trial order
  randomizeTrials();

  // Resets performance variables
  hits = 0;
  misses = 0;

  current_trial = 0;
  continue_button.remove();

  // Shows the targets again
  draw_targets = true;
}

// Creates and positions the UI targets
function createTargets(target_size, horizontal_gap, vertical_gap) {
  // Define the margins between targets by dividing the white space
  // for the number of targets minus one
  h_margin = horizontal_gap / (GRID_COLUMNS - 1);
  v_margin = vertical_gap / (GRID_ROWS - 1);

  let c = 3;
  let r = 1;
  for (const letter of letters) {
    // Find the appropriate label for this target
    const target_label = letter === "0" ? "0%" : letter;
    const arrowLabel = "<-";

    const target_x = 40 + (h_margin + target_size) * c + target_size / 2;
    const target_y = 40 + (v_margin + target_size) * r + target_size / 2;

    // Children
    const R_LENGTH = min(MAX_ROW_LENGTH, legendasObj[letter].length);
    let rChild = r + 1;
    let initialC = c - floor(R_LENGTH / 2);
    while (initialC + R_LENGTH > GRID_COLUMNS + 1) initialC--;
    let cChild = initialC;
    const children = {};
    for (const legenda of legendasObj[letter]) {
      let target_x = 40 + (h_margin + target_size) * cChild + target_size / 2;
      let target_y = 40 + (v_margin + target_size) * rChild + target_size / 2;

      // The word
      const target = new Target(
        target_x,
        target_y,
        target_size,
        legenda,
        false,
        true
      );
      children[legenda] = target;

      cChild++;
      if (cChild > c + R_LENGTH / 2) {
        cChild = initialC;
        rChild++;
      }
    }

    // The letter
    const target = new Target(
      target_x,
      target_y,
      target_size,
      target_label,
      true,
      !!Object.keys(children).length
    );

    // Back arrow
    const backArrow = new Target(
      target_x,
      target_y,
      target_size,
      arrowLabel,
      true,
      true
    );

    targets[letter] = {
      target: target,
      backArrow: backArrow,
      children: children,
    };

    c++;
    if (c > LETTERS_COLUMNS + 2) {
      c = 3;
      r++;
    }
  }
}

// Is invoked when the canvas is resized (e.g., when we go fullscreen)
function windowResized() {
  if (fullscreen()) {
    // DO NOT CHANGE THESE!
    resizeCanvas(windowWidth, windowHeight);
    let display = new Display({ diagonal: display_size }, window.screen);
    PPI = display.ppi; // calculates pixels per inch
    PPCM = PPI / 2.54; // calculates pixels per cm

    // Make your decisions in 'cm', so that targets have the same size for all participants
    // Below we find out out white space we can have between 2 cm targets
    let screen_width = display.width * 2.54; // screen width
    let screen_height = display.height * 2.54; // screen height
    let target_size = 4; // sets the target size (will be converted to cm when passed to createTargets)
    let horizontal_gap = screen_width - target_size * GRID_COLUMNS; // empty space in cm across the x-axis (based on 10 targets per row)
    let vertical_gap = screen_height - target_size * GRID_ROWS; // empty space in cm across the y-axis (based on 8 targets per column)

    // Creates and positions the UI targets according to the white space defined above (in cm!)
    // 80 represent some margins around the display (e.g., for text)
    createTargets(
      target_size * PPCM,
      horizontal_gap * PPCM - 80,
      vertical_gap * PPCM - 80
    );

    // Starts drawing targets immediately after we go fullscreen
    draw_targets = true;
  }
}
