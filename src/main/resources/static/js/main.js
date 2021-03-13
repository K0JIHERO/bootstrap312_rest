const userInfo = document.querySelector('.forFetch');
let output = '';

const url = '/getUsers/1';

fetch(url)
    .then(res => res.json())
    .then(user => { var userdata = user.roles

// {
//         // data.forEach(user => {
            output += `

                     <td>${user.id}</td>
                     <td>${user.firstName}</td>
                     <td>${user.lastName}</td>
                     <td>${user.age}</td>
                     <td>${user.email}</td>
                     <td>${userdata[0,1].role.replace('ROLE_', '')}</td>
`;

        userInfo.innerHTML = output; }
    )




//getAll

//     fetch('/getUsers')
//         .then(res => res.json())
//         .then(data => console.log(data))
// }

//create new user

// fetch('/getUsers', {
//     method: 'POST',
//     headers: {
//         'Content-Type' : 'application/json'
//     },
//     body: JSON.stringify({
//         name: 'user2'
//     })
// }).then(res => {
//      return res.json()
//     })
//     .then(data => console.log(data))
//     .catch(error => console.log('ERROR'))