var webSocket = (function () {

    var ws;
    var output;
    var uri = "ws://localhost:8080/admin";

    function init() {

        output = document.getElementById("output");
        ws = new WebSocket(uri);

        ws.onopen = function () {
            writeToScreen("Connected to " + uri);
        };

        ws.onmessage = function (evt) {
            writeToScreen(evt.data);
        };

        ws.onerror = function (evt) {
            writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
            ws.close();
        };
    }

    function sendUserId() {
        var userId = document.getElementById("userId").value;
        doSend(userId);
    }

    function doSend(message) {
        ws.send(message);
        writeToScreen("Message sent: " + message);
    }

    function writeToScreen(message) {
        var p = document.createElement("p");
        p.innerHTML = message;
        output.appendChild(p);
    }

    return {
        init: init,
        sendUserId: sendUserId
    };

})();


window.onload = function () {
    webSocket.init();
};

