const f = document.getElementById('form');
const q = document.getElementById('query');
const domain = 'http://localhost:8080/';
const site = 'awardmovies/';
//const id = 'id/';
const search = 'search-movie?';
const title = 'Title=';
//let year1;
const y = document.getElementById('year');
let category1;

function submitted(event) {
    event.preventDefault();
    if (q.value == '')
        alert("Input cannot be empty");
    else if (q.value.length < 3){
        alert("Incorrect input. Search name characters cannot be less than 3.")
    } else{
        const urlName = domain + site + search + title + q.value;
        const win = window.open(urlName, '_blank').focus();
    }
    // Some movies name are number, eg.1917. It will cause issues if enable search by id.
    //const urlId = localhost + site + id + q.value; 
    //const win = window.open(urlId, '_blank').focus();
}
f.addEventListener('submit', submitted);

const f1 = document.getElementById('form1');
/*function yearSelect(){
    year1 = document.getElementById('year').value;
}*/
function categorySelect(){
    category1 = document.getElementById('category').value;
}
function submitted1(event) {
    event.preventDefault();
    if (y.value == ''){
        alert("Incorrect input. Input year cannot be empty.");
    } else if (isNaN(y.value)){
        alert("Incorrect input. Input year is not a number.");
    } else if (y.value < 1980 || y.value > 2020){
        alert("Incorrect input. Input year should be between 1980-2020.");
    } else if (document.querySelector('#winner:checked') !== null){
        let urlName1 = domain + site + "winning/" + category1 + "/" + y.value;
        let win1 = window.open(urlName1, '_blank').focus();
    } else {
        //alert("category: "+ category1 + " , year: " + year1);
        let urlName1 = domain + site + category1 + "/" + y.value;
        let win1 = window.open(urlName1, '_blank').focus();
    }
}
f1.addEventListener('submit', submitted1);
