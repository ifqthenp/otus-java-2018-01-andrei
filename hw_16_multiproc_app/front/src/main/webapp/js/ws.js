var webSocket = (function () {

    var ws;
    var element;
    var stats = "stats";
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
            var object = JSON.parse(evt.data);
            var result = getHtmlString(object);
            writeToScreen(element, result);
        };

        ws.onerror = function (evt) {
            writeToScreen(status, '<span style="color: red;">ERROR:</span> ' + evt.data);
            ws.close();
        };
    }

    function getHtmlString(object) {
        var result = '';
        var i = 1;
        for (var key in object) {
            if (object.hasOwnProperty(key)) {
                result += '<tr><th scope="row">' + i++ + '</th><td>' + key + '</td><td>' + object[key] + '</td></tr>';
            }
        }
        return result;
    }

    function updateCache() {
        var message = {
            className: "cacheUpdateRequest"
        };
        element = stats;
        doSend(message);
    }

    function sendUserId() {
        var message = {
            message: document.getElementById("userId").value,
            className: "userDataByIdRequest"
        };
        element = response;
        doSend(message);
    }

    function doSend(message) {
        ws.send(JSON.stringify(message));
        writeToScreen(request, "Message sent: " + JSON.parse(message.type));
    }

    function writeToScreen(element, message) {
        document.getElementById(element).innerHTML = message;
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

