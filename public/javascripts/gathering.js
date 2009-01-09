$(function () {
    $("#week-container input").toggle();

    // TODO: Probably not portable? Do this the jQuery way.
    document.onmousedown = function(event) {
      console.log(event.target.tagName);
      if(/td|th|input|textarea/i.test(event.target.tagName)) {
        return false;
      };
    };
  });
