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
  const carAttributes = ['ID', 'Brand', 'Model', 'Registration', 'Seats', 'Color', 'Motor', 'Fuel', 'Fabric Date'];
  const mytable = document.getElementById("data-table");
  mytable.innerHTML = '';

  let initRow = document.createElement("tr");
  initRow.style = "width:100%";

  for (let index = 0; index < carAttributes.length; index++) {
    let cell = document.createElement("th");

    switch (index) {
      case '0':
      case '1':
      case '4': cell.style = "width:12%"; break;
      default: cell.style = "width:6%";
    }

    cell.innerText = carAttributes[index];
    initRow.appendChild(cell);
  }

  mytable.appendChild(initRow);

  data.forEach(car => {
    let newRow = document.createElement("tr");
    Object.values(car).forEach(value => { 
        let cell = document.createElement("td");
        cell.innerText = value;
        newRow.appendChild(cell);
      }
    )
    mytable.appendChild(newRow);
  });
}