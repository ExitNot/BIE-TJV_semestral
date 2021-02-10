// let search_form = document.querySelector(".roof form");
let create_form = document.querySelector(".create_modal form");
let search_form = document.getElementById("search_form_id");

// if(!create_form){
//     console.log(document.querySelector(".create_modal form"));
// } else{
//     alert("nakonecto eta huynya nahoditsya");
// }

function create_form_close() {
    document.getElementById('create_modal_id').style.display='none';
}

create_form.addEventListener("submit", function(e){
    e.preventDefault();
    const in_bookName = document.getElementById("bookName").value;
    const in_issueNumber = document.getElementById("issueNumber").value;
    const in_publishDate = document.getElementById("publishDate").value;
    const in_description = document.getElementById("description").value;
    const url = 'http://localhost:8080/api/v1/books';

    const data = {
        "bookName" : in_bookName,
        "issueNumber" : parseInt(in_issueNumber, 10),
        "publishDate" : in_publishDate,
        "description" : in_description
    };

    fetch(url, {
    method: 'POST',
    body: JSON.stringify(data),
    // json: true,
    headers: {
        'Accept' : 'application/json',
        'Content-Type' : 'application/json',
        'Access-Control-Allow-Origin' : 'http://localhost:8080',
        authorization : 'Setler nepredsk4zuemo'
    }
    }).then( async function(response){
        const json = await response.json(); // await
        console.log('Response:', json);
    }).catch(function(error){
        console.error('Error:', error);
    });
    
    // const response = await fetch(url, {
    //     method: 'POST', // *GET, POST, PUT, DELETE, etc.
    //     mode: 'cors', // no-cors, *cors, same-origin
    //     cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    //     credentials: 'same-origin', // include, *same-origin, omit
    //     headers: {
    //       'Content-Type': 'application/json'
    //       // 'Content-Type': 'application/x-www-form-urlencoded',
    //     },
    //     redirect: 'follow', // manual, *follow, error
    //     referrerPolicy: 'no-referrer', // no-referrer, *client
    //     body: JSON.stringify(data) // body data type must match "Content-Type" header
    //   });
// POST http://localhost:8080/api/v1/books
});

search_form.addEventListener("submit", function(e){
    e.preventDefault();
    get_request();
})

function get_request() {
    url = "http://localhost:8080/api/v1/books?page=0&size=2"
    
    fetch(url, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            authorization: 'Setler nepredsk4zuemo'
        }
        }).then( async function(response){
            // console.log('txt: ', response.text())
            const json = await response.json(); // await
            console.log('Response:', json);
        }).catch(function(error){
            console.error('Error:', error);
        });
        
}

// ~async function create_form_submit(e){
// }

// create_form.addEventListener("submit", create_form_submit());