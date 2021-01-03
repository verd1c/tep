'use strict';

const pages = {
    INIT: -1,
    VISIT: 0,
    PROFILE: 1,
}

const states = {
    INIT: -1,
    LOGGED_OUT: 0,
    LOGGED_IN: 1,
}

var session = {

    // States
    pageIn: pages.INIT,
    stateIn: states.INIT,

    // Callbacks
    pageChanged: pageChanged,
    stateChanged: stateChanged,

    // Page Get/Set
    get page(){
        return this.pageIn;
    },
    set page(value){
        this.pageIn = value;
        this.pageChanged(value);
    },

    // State Get/Set
    get state(){
        return this.stateIn;
    },
    set state(value){
        this.stateIn = value;
        stateChanged(value);
    },

    // Logged in username/userID/AMKA
    username: 'none',
    password: 'none',
    userID: 0,
    amka: 0,
}

function pageChanged(value){

}

function stateChanged(value){
    if(session.state == states.LOGGED_IN){
        $('#logoutBtn').css('display', 'flex');
        $('#lgnBtn').css('display', 'none');
    }else if(session.state == states.LOGGED_OUT){
        $('#logoutBtn').css('display', 'none');
        $('#lgnBtn').css('display', 'flex');
    }
}