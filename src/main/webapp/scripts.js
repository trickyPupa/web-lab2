const url = "controller"

function createError(message) {
    console.log(message);
    const error = document.getElementById("text-error");
    error.textContent = message;
}

const checkX = (value) => {
    return new Promise((resolve, reject) => {
        if (isNaN(value) || (-3) > value || value > 5) {
            reject("значение x некорректно");
        } else {
            resolve();
        }
    });
}

const checkY = (value) => {
    return new Promise((resolve, reject) => {
        if (isNaN(value) || (-3) > value || value > 5) {
            reject("значение y некорректно");
        } else {
            resolve();
        }
    });
}

const checkR = (value) => {
    return new Promise((resolve, reject) => {
        if (isNaN(value) || (1) > value || value > 5) {
            reject("значение r некорректно");
        } else {
            resolve();
        }
    });
}

function validate(x, y, r) {
    return Promise.all([
        checkX(x),
        checkY(y),
        checkR(r)
    ]);
}

function submitForm(event) {
    event.preventDefault();
    const x = document.getElementById("x");
    const y = document.querySelector('.btn.active');
    const r = document.querySelector('.r-checkbox:checked');
    createError("");

    if (!y) {
        createError("y не определен");
    }
    else if (!r) {
        createError("r не определен");
    }
    else {
        validate(x.value, y.value, r.value)
            .then(() => {
                sendData(x.value, y.value, r.value);
            }).catch((error) => {
                createError(error);
            });
    }
}

function sendData(x, y, r) {
    fetch(url + `?x=${x}&y=${y}&r=${r}`, { method: "GET" })
        .then(response => response.json())
        .then(data => {
            if (data.message){
                createError(data.message);
            }
            else {
                // addToTable(x, y, r, data.status, data.time, new Date().toLocaleTimeString());
                console.log("row added");
                // drawDot(x, y, r, data.status);
            }
        })
        .catch((err) => {
            createError(err)
        });
}

function addToTable(x, y, r, status, time, data) {
    const tableBody = document.getElementById("records-body");
    const row = document.createElement("tr");
    row.className = "row";
    const xtd = document.createElement("td");
    xtd.className = "item";
    xtd.textContent = x;
    row.appendChild(xtd);
    const ytd = document.createElement("td");
    ytd.className = "item";
    ytd.textContent = y;
    row.appendChild(ytd);
    const rtd = document.createElement("td");
    rtd.className = "item";
    rtd.textContent = r;
    row.appendChild(rtd);
    const stattd = document.createElement("td");
    stattd.className = "item";
    stattd.textContent = status;
    row.appendChild(stattd);
    const timetd = document.createElement("td");
    timetd.className = "item";
    timetd.textContent = time;
    row.appendChild(timetd);
    const datetd = document.createElement("td");
    datetd.className = "item";
    datetd.textContent = data;
    row.appendChild(datetd);
    tableBody.prepend(row);
}

function drawDot(x, y, r, status) {
    const canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');
    const formula = (coord, radius) => (200 + (4 * coord * 40) / radius);
    ctx.beginPath();
    ctx.fillStyle = status ? '#A8E4A0' : '#EE204D';
    ctx.moveTo(200, 200);
    ctx.arc(formula(x, r), formula(-y, r), 5, 0, 2 * Math.PI);
    ctx.fill();
    ctx.closePath();
    console.log("dot has been drawn");
}

function toggleActive(element, cls) {
    const elements = document.querySelectorAll(cls);
    elements.forEach(el => el.classList.remove('active'));

    element.classList.add('active');
}

function checkOnly(checkbox, cls) {
    const checkboxes = document.querySelectorAll(cls);

    checkboxes.forEach((chk) => {
        if (chk !== checkbox) {
            chk.checked = false;
        }
    });
}

function addDotByClick() {
    document.getElementById('canvas').addEventListener('click', function (event) {
        const svg = document.getElementById('graph');
        const pt = svg.createSVGPoint();
        const errorMessage = document.getElementById("error-message");

        errorMessage.textContent = "";

        pt.x = event.clientX;
        pt.y = event.clientY;
        const cursorPt = pt.matrixTransform(svg.getScreenCTM().inverse());

        const rValue = parseFloat(document.getElementById('r').value);

        checkR(rValue)
            .catch((error) => createError(error));

        const scaledX = (cursorPt.x / 100) * rValue;
        const scaledY = -(cursorPt.y / 100) * rValue;

        document.getElementById('x').value = scaledX.toFixed(2);
        document.getElementById('y').value = scaledY.toFixed(2);

        const xValue = parseFloat(document.getElementById('x').value);
        const yValue = parseFloat(document.getElementById('y').value);

        validate(xValue, yValue, rValue)
            .then(() => {
                sendData(xValue, yValue, rValue);
            })
            .catch((error) => {
                createError(error)
            })
    });
}

// addToTable(1, 1, 1, true, 12, 'now');