setInterval(main, 1000);

function main() {
  fetch('/vehicle/list')
    .then(function (response) {
      return response.json();
    }).then(function (apiJsonData) {
      renderDataInTheTable(apiJsonData);
    })
}

function renderDataInTheTable(data) {
  const arrayTitle = ['ID', 'Brand', 'Model', 'Registration', 'Seats', 'Color', 'Motor', 'Fuel', 'Fabric Date'];
  const mytable = document.getElementById("data-table");
  mytable.innerHTML = '';

  let initRow = document.createElement("tr");
  initRow.style = "width:100%";

  for (let index = 0; index < arrayTitle.length; index++) {
    let cell = document.createElement("th");

    switch (index) {
      case '0':
      case '1':
      case '4': cell.style = "width:12%"; break;
      default: cell.style = "width:6%";
    }

    cell.innerText = arrayTitle[index];
    initRow.appendChild(cell);
  }

  mytable.appendChild(initRow);

  data.forEach(sample => {
    let newRow = document.createElement("tr");
    Object.values(sample).forEach((value) => {
      let cell = document.createElement("td");
      cell.innerText = value;
      newRow.appendChild(cell);
    })
    mytable.appendChild(newRow);
  });
}