'use strict';

// A generalized ajax request
function ajaxRequest(method, url, data, callback, failedcallback){
  var request = new XMLHttpRequest();
  var postData = '';

  // If method is GET, create the url
  if(data !== undefined){
    if(method === 'GET'){
      url += '?'; // Query

      // Add query parameters
      for (var pair of data.entries()) {
        url += pair[0] + '=' + pair[1] + '&';
      }

      url = url.slice(0, -1); // Remove last &
    }else{
      postData = '';
      // url += '?'; // Query

      // Add query parameters
      for (var pair of data.entries()) {
        postData += pair[0] + '=' + pair[1] + '&';
      }

      postData = postData.slice(0, -1); // Remove last &
    }
  }

  // Set callback
  request.onreadystatechange = function() {
    if(this.readyState == 4 && this.status == 200){
      if (callback !== undefined) callback(this);
    }else{
      if (failedcallback != undefined && this.readyState == 4) failedcallback(this);
    }
  };

  // Make the request with the appropriate data
  request.open(method, url, true);
  request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  request.send(postData);
}
