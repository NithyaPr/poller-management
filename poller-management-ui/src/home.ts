function openNav() {
    let mySidenav = document.getElementById('mySidenav')
    if(mySidenav) (mySidenav as HTMLFormElement).style.width = "250px"; 
}

function closeNav () {
    let mySidenav = document.getElementById('mySidenav')
    if(mySidenav) (mySidenav as HTMLFormElement).style.width = "0px"; 
}

export {openNav, closeNav}