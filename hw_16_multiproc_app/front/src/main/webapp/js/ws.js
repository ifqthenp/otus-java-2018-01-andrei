var webSocket = (function () {

    var ws;
    var status = "status";
    var request = "request";
    var response = "response";
    var uri = "ws://localhost:8080/admin";

    function init() {

        ws = new WebSocket(uri);

        ws.onopen = function () {
            writeToScreen(status, "Connected to " + uri);
        };

        ws.onmessage = function (evt) {
            writeToScreen(response, evt.data);
        };

        ws.onerror = function (evt) {
            writeToScreen(status, '<span style="color: red;">ERROR:</span> ' + evt.data);
            ws.close();
        };
    }

    function updateCache() {

    }

    function sendUserId() {
        var userId = document.getElementById("userId").value;
        doSend(userId);
    }

    function doSend(message) {
        ws.send(message);
        writeToScreen(request, "Message sent: " + message);
    }

    function writeToScreen(element, message) {
        document.getElementById(element).innerText = message;
    }

    return {
        init: init,
        sendUserId: sendUserId,
        updateCache: updateCache
    };

})();


window.onload = function () {
    webSocket.init();
};

