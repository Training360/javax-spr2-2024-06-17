fetch("http://localhost:8080/api/employees")
    .then(response => response.json())
    .then(employees => print(employees));

function print(employees) {
    const table = document.querySelector("#employees-table");
    for (const employee of employees) {
        table.innerHTML = `<tr><td>${employee.id}</td><td>${employee.name}</td></tr>`;
    }
}