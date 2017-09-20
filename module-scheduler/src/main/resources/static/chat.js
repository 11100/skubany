var stompClient = null;
var allColors = ["black", "red", "green", "blue", "orange", "gray", "pink", "lime", "margenta", "brown", "darkblue", "darkgreen"];
var userColor = "orange";

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('loginPanel').style.visibility = connected ? 'hidden' : 'visible';
    document.getElementById('chatPanel').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('loginPanel').style.display = connected ? 'none' : 'block';
    document.getElementById('chatPanel').style.display = connected ? 'block' : 'none';
    document.getElementById('response').innerHTML = '';
    if(connected){
    	$('#content').focus();
    } else {
    	$('#name').focus();
    }
}

function connect() {
    var hostname = window.location.href;
    var filename = "chat.html";
    userColor = allColors[Math.floor(Math.random() * allColors.length)];
    hostname = hostname.substr(0, hostname.length-filename.length);
    var socket = new SockJS(hostname+'/hello');
    stompClient = Stomp.over(socket);            
    stompClient.connect({}, function(frame) {
        setConnected(true);
        stompClient.subscribe('/topic/greetings', function(greeting){
            showGreeting(
                JSON.parse(greeting.body).name,
                JSON.parse(greeting.body).content,
                JSON.parse(greeting.body).color
            );
        });

        sendName("Connecting...");
    });
}

function disconnect() {
	sendName("Disconnecting...");
    stompClient.disconnect();
    setConnected(false);
}

function sendName(content) {
    var name = document.getElementById('name').value;
    if(content == "userContent"){
    	content = document.getElementById('content').value;
    }
    stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name, 'content': content, 'color': userColor }));
    document.getElementById('content').value = '';
}

function showGreeting(name, message, color) {
    var li = document.createElement('li');
    var span = document.createElement('span');
    li.style.color = color;
    span.appendChild(document.createTextNode(name + ": "));
    li.appendChild(span);
    li.appendChild(document.createTextNode(message));
    $("#response").prepend(li);
}


///////////////////////////////////////////////////////////////////
/// enter key automation begin
///////////////////////////////////////////////////////////////////

$.fn.enterKey = function (fnc) {
    return this.each(function () {
        $(this).keypress(function (ev) {
            var keycode = (ev.keyCode ? ev.keyCode : ev.which);
            if (keycode == '13') {
                fnc.call(this, ev);
            }
        });
    });
};

$(document).ready(function() {

	$('#name').focus();
	
	$("input#content").enterKey(function () {
		sendName("userContent");
	});

	$("input#name").enterKey(function () {
		connect();
	});
});

///////////////////////////////////////////////////////////////////
/// enter key automation end
/////////////////////////////////////////////////////////////////////

