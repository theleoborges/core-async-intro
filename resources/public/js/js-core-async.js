var messageChannel = new MessageChannel();
var tasks = [];

messageChannel.port1.onmessage = function(msg) {
  tasks.shift()();
};

var c = [];

function publishValue(value, timeout) {
  setTimeout(function() {
    c.push(value);
    publishValue(value, timeout);
  }, timeout);
}

publishValue(1, 250);
publishValue(2, 1000);
publishValue(3, 1500);



function renderValues(q) {
  tasks.push(function() {
    var v = c.shift();
    if (v) {
      q.unshift(v);
      q = q.slice(0,10);
      var result = q.reduce(function(acc,p){
        return acc+ "<div class='proc-" + p + "'>Process " + p + "</div>";
      },"");
      document.getElementById("messages1").innerHTML = result;
    }
    renderValues(q);
  });
  messageChannel.port2.postMessage(0);
}

//renderValues([]);
