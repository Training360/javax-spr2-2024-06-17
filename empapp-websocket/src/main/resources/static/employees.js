window.onload = function () {
    const socket = new SockJS("/websocket-endpoint");
    const client = Stomp.over(socket);

    const button = document.querySelector("#message-button");
    button.onclick = function() {
        const messageInput = document.querySelector("#message-input");
        const content = messageInput.value;
        const json = {"content": content};
        console.log("Send json with STOMP over WebSocket over SockJS: ", json);
        client.send("/app/messages", {}, JSON.stringify(json));
    }
}