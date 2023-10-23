const canvas = document.getElementById('canvas');
// drawCanvas();
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("canvas").addEventListener("click", (e) => {
        let rr = document.getElementById("form:r_chooser");
        let r = rr.value;
        let maslo = document.getElementById('canvas');
        let event_x = e.pageX - maslo.offsetLeft;
        let event_y = e.pageY - maslo.offsetTop;
        let x = (event_x - 250) * r / 200;
        let y = (250 - event_y) * r / 200;
        let submitX = document.getElementById("hiddenform:x_hidden_chooser");
        let submitY = document.getElementById("hiddenform:y_hidden_chooser");
        let submitR = document.getElementById("hiddenform:r_hidden_chooser");
        submitX.value = x;
        submitY.value = y;
        submitR.value = r;
        let hiddenSubmit = document.getElementById("hiddenform:hiddensubmit");
        hiddenSubmit.click();
        drawPoints();
    })
});
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("form:submit").addEventListener("click", (e) => {
        setTimeout(drawCanvas, 100);
    })
});
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("form:r_chooser").addEventListener("change", (e) => {
        setTimeout(drawCanvas, 100);
    })
});

function drawCanvas() {
    var spinnerInput = document.getElementById("form:r_chooser_input");
    console.log(spinnerInput);
    console.log(spinnerInput.value);
    let r_text = spinnerInput.value + "";
    let rhalf_text = spinnerInput.value / 2 + "";
    let canvas = document.getElementById("canvas"),
        context = canvas.getContext("2d");
    canvas.width = 500;
    canvas.height = 500;
    context.clearRect(0, 0, 500, 500);
    context.fillStyle = "#E8D7FF";
    context.fillRect(50, 250, 200, 100);
    context.beginPath();
    context.moveTo(250, 250);
    context.lineTo(450, 250);
    context.lineTo(250, 450);
    context.fill();
    context.moveTo(250, 250);
    context.arc(250, 250, 100, Math.PI, Math.PI * 3 / 2);
    context.fill();
    context.beginPath();
    context.strokeStyle = "#FF47A0";
    context.lineWidth = 2;
    context.moveTo(0, 250);
    context.lineTo(500, 250);
    context.stroke();
    context.beginPath();
    context.strokeStyle = "#FF47A0";
    context.lineWidth = 2;
    context.moveTo(250, 500);
    context.lineTo(250, 0);
    context.stroke();
    context.strokeText(rhalf_text, 350, 250);
    context.strokeText(r_text, 450, 250);
    context.strokeText(rhalf_text, 250, 350);
    context.strokeText(r_text, 250, 450);
    context.strokeText(rhalf_text, 150, 250);
    context.strokeText(rhalf_text, 250, 150);
    context.strokeText(r_text, 250, 50);
    context.strokeText(rhalf_text, 350, 250);
    context.strokeText(r_text, 50, 250);
    context.strokeText("Y", 250, 10);
    context.strokeText("X", 490, 250);
    drawPoints();
}

function drawPoint(x, y, r, result) {
    let rr = document.getElementById("form:r_chooser_input");
    let rValue = rr.value;
    let finalX = 250 + x * 200 / rValue;
    let finalY = 250 - y * 200 / rValue;
    let canvas = document.getElementById("canvas"),
        context = canvas.getContext("2d");
    if (result === "false") {
        context.fillStyle = "#f0dfde";
    } else {
        context.fillStyle = "#5FFF33";
    }
    context.beginPath();
    context.arc(finalX, finalY, 5, 0, 2 * Math.PI);
    context.fill();
    context.stroke();
    context.closePath();
}

function drawPoints() {
    let coordinates = Array.prototype.slice.call(document.getElementById("resultTable").getElementsByTagName("td"));
    // console.log("Day la cai toi de ve diem " + coordinates);
    for (let i = 0; i < coordinates.length; i = i + 4) {
        console.log(Number(coordinates[i].textContent));
        drawPoint(Number(coordinates[i].textContent),
            Number(coordinates[i + 1].textContent),
            Number(coordinates[i + 2].textContent), coordinates[i + 3].textContent);
    }
}

function changeR() {
    drawCanvas();
}