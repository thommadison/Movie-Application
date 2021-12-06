// constants
const SEARCHFORM = document.getElementById('form');
const SEARCHINPUT = document.getElementById('query');
const ADVANCEDSEARCHFORM = document.getElementById('form1');
const DOMAIN = 'http://localhost:8080/';
const SITE = 'awardmovies/';
const SEARCH = 'search-movie?';
const TITLE = 'Title=';
const WINNER = 'winning/';
const CATEGORY = 'category/';
const YEAR = 'year/';
let searchYear = document.getElementById('year');
let searchFromYear = document.getElementById('fromYear');
let searchToYear = document.getElementById('toYear');
let searchCategory;
let flag = 0;

// Advanced search category select function
function getCategorySelect() {
    searchCategory = document.getElementById('category').value;
}

// Advanced search use range button function
document.getElementById('range').onclick = function() {
    let disabledYear = document.getElementById("year").disabled;
    let disabledWinner = document.getElementById("winner").disabled;
    // Disable year inputbox
    if (disabledYear) {
        flag = 0;
        document.getElementById("year").disabled = false;
    } else {
        flag = 1;
        document.getElementById("year").disabled = true;
    }
    // Disable winner checkbox
    if (disabledWinner) {
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
    if (SEARCHINPUT.value == '')
        alert("Input cannot be empty");
    else if (SEARCHINPUT.value.length < 3)
        alert("Incorrect input. Search name characters cannot be less than 3.")
    else {
        searchTitleUrl = DOMAIN + SITE + SEARCH + TITLE + SEARCHINPUT.value;
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
        if (searchYear.value == '')
            alert("Incorrect input. Input year cannot be empty.");
        else if (isNaN(searchYear.value))
            alert("Incorrect input. Input year is not a number.");
        else if (searchYear.value < 1980 || searchYear.value > 2020)
            alert("Incorrect input. Input year should be between 1980-2020.");
        else if (searchCategory == undefined)
            alert("Please select category");
        else if (document.querySelector('#winner:checked') !== null) {
            searchWinnerCateUrl = DOMAIN + SITE + WINNER + CATEGORY + searchCategory + "/" + YEAR + searchYear.value;
            win = openTab(searchWinnerCateUrl);
        } else {
            searchCateYearUrl = DOMAIN + SITE + CATEGORY + searchCategory + "/" + YEAR + searchYear.value;
            win = openTab(searchCateYearUrl);
        }
    } else {
        if (searchFromYear.value == '' || searchToYear.value == '')
            alert("Incorrect input. Input year cannot be empty.");
        else if (isNaN(searchFromYear.value) || isNaN(searchToYear.value))
            alert("Incorrect input. Input year is not a number.");
        else if (searchFromYear.value < 1980 || searchFromYear.value > 2020 || searchToYear.value < 1980 || searchToYear.value > 2020) 
            alert("Incorrect input. Input year should be between 1980-2020.");
        else if (searchFromYear.value >= searchToYear.value)
            alert("Incorrect input. Input from year should be less than to year.");
        else if (searchCategory == undefined)
            alert("Please select category");
        else {
            searchRangeCateUrl = DOMAIN + SITE + CATEGORY + searchCategory + "/start/" + searchFromYear.value + "/end/" + searchToYear.value;
            win = openTab(searchRangeCateUrl);
        }
    }

    function openTab(searchUrl) {
        return window.open(searchUrl, '_blank').focus();
    }
}
// Event Listener
SEARCHFORM.addEventListener('submit', submitted);
ADVANCEDSEARCHFORM.addEventListener('submit', submitted1);