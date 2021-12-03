const f = document.getElementById('form');
const q = document.getElementById('query');
const f1 = document.getElementById('form1');
const domain = 'http://localhost:8080/';
const site = 'awardmovies/';
const search = 'search-movie?';
const title = 'Title=';
const winner = 'winning/';
let y = document.getElementById('year');
let fy = document.getElementById('fromYear');
let ty = document.getElementById('toYear');
let category1;
let flag = 0;

function categorySelect() {
    category1 = document.getElementById('category').value;
}

document.getElementById('range').onclick = function() {
    let disabled = document.getElementById("year").disabled;
    let disabled1 = document.getElementById("winner").disabled;
    if (disabled) {
        flag = 0;
        document.getElementById("year").disabled = false;
    } else {
        flag = 1;
        document.getElementById("year").disabled = true;
    }
    if (disabled1) {
        flag = 0;
        document.getElementById("winner").disabled = false;
    } else {
        flag = 1;
        document.getElementById("winner").disabled = true;
    }
}

function submitted(event) {
    event.preventDefault();
    if (q.value == '')
        alert("Input cannot be empty");
    else if (q.value.length < 3)
        alert("Incorrect input. Search name characters cannot be less than 3.")
    else {
        let urlName = domain + site + search + title + q.value;
        let win = window.open(urlName, '_blank').focus();
    }
}

function submitted1(event) {
    event.preventDefault();
    if (flag == 0) {
        if (y.value == '')
            alert("Incorrect input. Input year cannot be empty.");
        else if (isNaN(y.value))
            alert("Incorrect input. Input year is not a number.");
        else if (y.value < 1980 || y.value > 2020)
            alert("Incorrect input. Input year should be between 1980-2020.");
        else if (document.querySelector('#winner:checked') !== null) {
            let urlName1 = domain + site + winner + category1 + "/" + y.value;
            let win1 = window.open(urlName1, '_blank').focus();
        } else {
            let urlName1 = domain + site + category1 + "/" + y.value;
            let win1 = window.open(urlName1, '_blank').focus();
        }
    } else {
        if (fy.value == '' || ty.value == '')
            alert("Incorrect input. Input year cannot be empty.");
        else if (isNaN(fy.value) || isNaN(ty.value))
            alert("Incorrect input. Input year is not a number.");
        else if (fy.value < 1980 || fy.value > 2020 || ty.value < 1980 || ty.value > 2020) 
            alert("Incorrect input. Input year should be between 1980-2020.");
        else if (fy.value >= ty.value)
            alert("Incorrect input. Input from year should be less than to year.");
        else {
            let urlName1 = domain + site + 'category/' + category1 + "/start/" + fy.value + "/end/" + ty.value;
            let win1 = window.open(urlName1, '_blank').focus();
        }
    }
}

f.addEventListener('submit', submitted);
f1.addEventListener('submit', submitted1);