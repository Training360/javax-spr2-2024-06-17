window.onload = function () {
    const socket = new SockJS("/websocket-endpoint");
    const client = Stomp.over(socket);

    client.connect({}, function (frame) {
        console.log("Connected")
        client.subscribe('/topic/employees', function (message) {
            console.log("Message has come: ", message);
            const text = JSON.parse(message.body).text;
            console.log(text);

            const div = document.querySelector("#message-div");
            div.innerHTML += `<p>${text}</p>`;
        });
    });

    const button = document.querySelector("#message-button");
    button.onclick = function() {
        const messageInput = document.querySelector("#message-input");
        const content = messageInput.value;
        const json = {"content": content};
        console.log("Send json with STOMP over WebSocket over SockJS: ", json);
        client.send("/app/messages", {}, JSON.stringify(json));
    }
}