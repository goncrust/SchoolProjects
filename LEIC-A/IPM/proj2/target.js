// Target class (position and width)
class Target {
  constructor(x, y, w, l, il, c) {
    this.x = x;
    this.y = y;
    this.width = w;
    this.label = l.replace(" ", "\n");
    this.isLetter = il;
    this.clickable = c;
  }

  // Checks if a mouse click took place
  // within the target
  clicked(mouse_x, mouse_y) {
    return (
      this.clickable && dist(this.x, this.y, mouse_x, mouse_y) < this.width / 2
    );
  }

  // Draws the target (i.e., a circle) and its label
  draw() {
    // Draw target
    fill(this.clickable ? color(255, 255, 255) : color(70, 70, 70));
    circle(this.x, this.y, this.width);

    // Draw label
    textFont("Arial", this.isLetter ? 40 : 23);
    fill(color(0, 0, 0));
    textAlign(CENTER);
    if (this.isLetter) text(this.label, this.x, this.y + 10);
    else
      text(
        this.label,
        this.x,
        this.label.includes("\n") ? this.y - 7 : this.y + 5
      );
  }
}
