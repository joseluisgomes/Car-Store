setInterval(main, 1000);

function main() {
  fetch('/employee/list')
    .then(function (response) {
      return response.json();
    }).then(function (apiJsonData) {
      renderDataInTheTable(apiJsonData);
    })
}

function renderDataInTheTable(data) {
  const employeeAttributes = ['ID', 'Name', 'Email', 'Role', 'Phone', 'Profile'];
  const mytable = document.getElementById("data-table");
  mytable.innerHTML = '';

  let initRow = document.createElement("tr");
  initRow.style = "width:100%";

  for (let index = 0; index < employeeAttributes.length; index++) {
    let cell = document.createElement("th");

    switch (index) {
      case '0':
      case '1': 
      case '3': cell.style = "width:12%"; break;
      default: cell.style = "width:6%";
    }
    cell.innerText = employeeAttributes[index];
    initRow.appendChild(cell);
  }
  mytable.appendChild(initRow);

  data.forEach(employee => {
    let newRow = document.createElement("tr");
    Object.values(employee).forEach(attribute => { 
        let cell = document.createElement("td");
        cell.innerText = attribute;
        newRow.appendChild(cell);
      }
    )
    mytable.appendChild(newRow);
  });
}