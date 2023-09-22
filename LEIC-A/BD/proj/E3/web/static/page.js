function nextPage(btn, idPrefix = "") {
  const page = btn.parentElement;
  const pageNum = parseInt(page.id.replace(idPrefix, ""), 10);
  console.log(pageNum);
  const next = document.getElementById(idPrefix + (pageNum + 1));

  page.style.display = "none";
  next.style.display = "block";
}

function previousPage(btn, idPrefix = "") {
  const page = btn.parentElement;
  const pageNum = parseInt(page.id.replace(idPrefix, ""), 10);
  const previous = document.getElementById(idPrefix + (pageNum - 1));

  page.style.display = "none";
  previous.style.display = "block";
}
