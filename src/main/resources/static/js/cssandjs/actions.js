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
let useRangeFlag = 0;

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
        useRangeFlag = 0;
        document.getElementById("year").disabled = false;
    } else {
        useRangeFlag = 1;
        document.getElementById("year").disabled = true;
    }
    // Disable winner checkbox
    if (disabledWinner) {
        useRangeFlag = 0;
        document.getElementById("winner").disabled = false;
    } else {
        useRangeFlag = 1;
        document.getElementById("winner").disabled = true;
    }
}

// Normal search (search by movie title) submit button function
function submitted(event) {
    let searchTitleUrl;
    let win;
    let validFlag = 0;
    event.preventDefault();
    validFlag = searchYearCheck();
    if (validFlag == 0) {
        searchTitleUrl = DOMAIN + SITE + SEARCH + TITLE + SEARCHINPUT.value;
        win = openTab();
    }

    function searchYearCheck() {
        let validFlag = 0;
        if (SEARCHINPUT.value == '') {
            alert("Input cannot be empty");
            validFlag = 1;
        } else if (SEARCHINPUT.value.length < 3) {
            alert("Incorrect input. Search name characters cannot be less than 3.");
            validFlag = 1;
        }
        return validFlag;
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
    let validFlag;
    event.preventDefault();
    if (useRangeFlag == 0) {
        let winnerCheckbox = document.querySelector('#winner:checked');
        validFlag = advancedSearchCheck();
        if (validFlag == 0) {
            if (winnerCheckbox !== null) {
                searchWinnerCateUrl = DOMAIN + SITE + WINNER + CATEGORY + searchCategory + "/" + YEAR + searchYear.value;
                win = openTab(searchWinnerCateUrl);
            } else {
                searchCateYearUrl = DOMAIN + SITE + CATEGORY + searchCategory + "/" + YEAR + searchYear.value;
                win = openTab(searchCateYearUrl);
            }
        }
    } else {
        validFlag = advancedSearchRangeYearCheck();
        if (validFlag == 0) {
            searchRangeCateUrl = DOMAIN + SITE + CATEGORY + searchCategory + "/start/" + searchFromYear.value + "/end/" + searchToYear.value;
            win = openTab(searchRangeCateUrl);
        }
    }

    function advancedSearchCheck() {
        let validFlag = 0;
        if (searchYear.value == '') {
            alert("Incorrect input. Input year cannot be empty.");
            validFlag = 1;
        } else if (isNaN(searchYear.value)) {
            alert("Incorrect input. Input year is not a number.");
            validFlag = 1;
        } else if (searchYear.value < 1980 || searchYear.value > 2020) {
            alert("Incorrect input. Input year should be between 1980-2020.");
            validFlag = 1;
        } else if (searchCategory == undefined) {
            alert("Please select category");
            validFlag = 1;
        }
        return validFlag;
    }
    
    function advancedSearchRangeYearCheck() {
        let validFlag = 0;
        if (searchFromYear.value == '' || searchToYear.value == '') {
            alert("Incorrect input. Input year cannot be empty.");
            validFlag = 1;
        } else if (isNaN(searchFromYear.value) || isNaN(searchToYear.value)) {
            alert("Incorrect input. Input year is not a number.");
            validFlag = 1;
        } else if (searchFromYear.value < 1980 || searchFromYear.value > 2020 || searchToYear.value < 1980 || searchToYear.value > 2020) {
            alert("Incorrect input. Input year should be between 1980-2020.");
            validFlag = 1;
        } else if (searchFromYear.value >= searchToYear.value) {
            alert("Incorrect input. Input from year should be less than to year.");
            validFlag = 1;
        } else if (searchCategory == undefined) {
            alert("Please select category");
            validFlag = 1;
        }
        return validFlag;
    }

    function openTab(searchUrl) {
        return window.open(searchUrl, '_blank').focus();
    }
}
// Event Listener
SEARCHFORM.addEventListener('submit', submitted);
ADVANCEDSEARCHFORM.addEventListener('submit', submitted1);