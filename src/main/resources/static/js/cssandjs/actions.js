// constants
const F = document.getElementById('form');
const Q = document.getElementById('query');
const F1 = document.getElementById('form1');
const DOMAIN = 'http://localhost:8080/';
const SITE = 'awardmovies/';
const SEARCH = 'search-movie?';
const TITLE = 'Title=';
const WINNER = 'winning/';
const CATEGORY = 'category/';
const YEAR = 'year/';
let y = document.getElementById('year');
let fy = document.getElementById('fromYear');
let ty = document.getElementById('toYear');
let category1;
let flag = 0;

// Advanced search category select function
function categorySelect() {
    category1 = document.getElementById('category').value;
}

// Advanced search use range button function
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

// Normal search (search by movie title) submit button function
function submitted(event) {
    let searchTitleUrl;
    let win;
    event.preventDefault();
    if (Q.value == '')
        alert("Input cannot be empty");
    else if (Q.value.length < 3)
        alert("Incorrect input. Search name characters cannot be less than 3.")
    else {
        searchTitleUrl = DOMAIN + SITE + SEARCH + TITLE + Q.value;
        win = openTab();
    }

    function openTab() {
        return window.open(searchTitleUrl, '_blank').focus();
    }
}

// Advanced search submit button function
function submitted1(event) {
    let searchWinnerCateUrl;
    let searchCateYearUrl;
    let searchRangeCateUrl;
    let win;
    event.preventDefault();
    if (flag == 0) {
        if (y.value == '')
            alert("Incorrect input. Input year cannot be empty.");
        else if (isNaN(y.value))
            alert("Incorrect input. Input year is not a number.");
        else if (y.value < 1980 || y.value > 2020)
            alert("Incorrect input. Input year should be between 1980-2020.");
        else if (document.querySelector('#winner:checked') !== null) {
            searchWinnerCateUrl = DOMAIN + SITE + WINNER + CATEGORY + category1 + "/" + YEAR + y.value;
            win = openTab(searchWinnerCateUrl);
        } else {
            // searchCateYearUrl = DOMAIN + SITE + category1 + "/" + y.value;
            searchCateYearUrl = DOMAIN + SITE + CATEGORY + category1 + "/" + YEAR + y.value;
            win = openTab(searchCateYearUrl);
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
            searchRangeCateUrl = DOMAIN + SITE + CATEGORY + category1 + "/start/" + fy.value + "/end/" + ty.value;
            win = openTab(searchRangeCateUrl);
        }
    }

    function openTab(searchUrl) {
        return window.open(searchUrl, '_blank').focus();
    }
}
// Event Listener
F.addEventListener('submit', submitted);
F1.addEventListener('submit', submitted1);