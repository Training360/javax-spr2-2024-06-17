const eventSource = new EventSource("api/employees/messages")
eventSource.addEventListener("create_employee", function(event) {
    const data = event.data;
    const json = JSON.parse(data);
    const text = json.text;
    console.log("Message: ", text);

    const div = document.querySelector("#messages-div");
    div.innerHTML += `<p>${text}</p>`;
});