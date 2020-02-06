// ===============================================================
// @group: Internal worker functions
// =============================================================== 

(function($) {
  $.extend($.fn, {    
    // ----------------------------------------------------------------
    // Trigger callback when fakeLoader is loaded
    // ----------------------------------------------------------------
    isPageLoaderDone: function(callback) {
      var $loader = $('[data-toggle="page-loader"]');
      var triggerCallback = function() {
          $('html').addClass('.page-loader-done');

          if (callback && typeof(callback) === "function") {
            callback();
          }
        };

      if ($loader.length === 0 || $loader.css('display') == 'none') {
        triggerCallback();
      }

      var isPageLoaderDoneTimer = setInterval(function() {
        if ($loader.css('display') == 'none') {
          Globals.PAGELOADER_DONE = true;
          clearInterval(isPageLoaderDoneTimer);
          triggerCallback();
        }
      }, 500);
    },

    // ----------------------------------------------------------------
    // Plugin: Waypoints
    // @see: http://imakewebthings.com/waypoints/
    // Used as helper for other functions so not called direct
    // ----------------------------------------------------------------
    includeWaypoints: function(callback) {
      if (typeof jQuery.fn.waypoint === 'undefined') {
        $document.themeLoadPlugin(['https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.min.js'], []);
        var tries = 0;
        var isWaypointsDoneTimer = setInterval(function() {
          if (typeof jQuery.fn.waypoint === 'function') {
            clearInterval(isWaypointsDoneTimer);

            if (callback && typeof(callback) === "function") {
              callback();
            }
          }
          tries++;

          if (tries > 20) {
            clearInterval(isWaypointsDoneTimer);
            alert('Error: Waypoints plugin could not be loaded');
          }
        }, 500);
      } else {
        if (callback && typeof(callback) === "function") {
          callback();
        }
      }
    },
    
    refreshWaypoints: function() {
      if (typeof jQuery.fn.waypoint !== 'undefined') {
        Waypoint.refreshAll();
      }      
    },

    // ----------------------------------------------------------------
    // Determine if element is in viewport
    // @credit: https://coderwall.com/p/fnvjvg/jquery-test-if-element-is-in-viewport
    // ----------------------------------------------------------------    
    elementInView: function(obj) {
      var elementTop = obj.offset().top;
      var elementBottom = elementTop + obj.outerHeight();
      var viewportTop = $(window).scrollTop();
      var viewportBottom = viewportTop + $(window).height();
      return elementBottom > viewportTop && elementTop < viewportBottom;
    },

    // ----------------------------------------------------------------
    // Scroll links
    // ----------------------------------------------------------------
    themeScrollMenus: function() {
      var context = $(this);
      var scrollLinks = context.find('[data-toggle="scroll-link"]');
      var $header = $('#header [data-toggle="sticky"]');
      var $body = $('body');
      var $window = $(window);
      var $spys = $('[data-spy="scroll"]');
      var spyTarget = $body.data('spy-target') || '.navbar-main';
      var spyData;

      if (scrollLinks.length > 0) {
        var getHeaderOffset = function(extra) {
          var offset = $header.outerHeight() + (extra !== null ? extra : 0);
          if ($body.hasClass('header-compact-sticky')) {
            offset = offset - 35;
          }
          
          if ($body.data('offset-elements')) {
            $($body.data('offset-elements'), context).each(function() {
              offset += $(this).outerHeight();
            });
          }
          
          return offset;
        };
        
        var customActiveSpyClass = function(active) {
          scrollLinks.each(function() {
            var $t = $(this);
            var customActive = $t.data('active-class') || null;
            
            if (active === null && customActive !== null) {
              $t.removeClass(customActive);  
            }
            else {
              if ($t.attr('href') == active) {
                $t.addClass(customActive);
              }
              else if (customActive !== null) {
                $t.removeClass(customActive);
              }
            }
          });          
        }

        var triggerSpy = function(state) {
          var offsetExtra = 5;
          if (state == 'refresh') {
            spyData = $body.data('bs.scrollspy');
            spyData._config.offset = getHeaderOffset(offsetExtra);
            $body.data('bs.scrollspy', spyData);
            $body.scrollspy('refresh');
          } else {
            $body.scrollspy({
              target: spyTarget,
              offset: getHeaderOffset(offsetExtra),
            });
          }
        };

        triggerSpy('init');
       
        // Custom active class per link
        spyData = $body.data('bs.scrollspy');
        if (spyData._activeTarget) {
          customActiveSpyClass(spyData._activeTarget);
        }
        $window.on('activate.bs.scrollspy', function(e, t) {
          customActiveSpyClass(t.relatedTarget);
        });
        $window.on('scroll.bs.scrollspy', function(e) {
          setTimeout(function() {
            customActiveSpyClass($body.data('bs.scrollspy')._activeTarget);
          }, 200);
        });
        
        $window.on('resize', function() {
          setTimeout(function() {
            triggerSpy('refresh');
          }, 200);
        });

        scrollLinks.click(function() {
          if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var $this = $(this),
              target = $(this.hash),
              speed = $this.data('scroll-link-speed') || 1000,
              noOffset = $this.data('scroll-link-nooffset') || false;

            var clickScroll = function() {
              var hash = this.hash || null;
              target = target.length ? target : (hash !== null ? $('[id=' + this.hash.slice(1) + ']') : null);
              if (target !== null) {
                offset = getHeaderOffset(null);
                if (noOffset) {
                  offset = 0;
                }

                $('html, body').animate({
                  scrollTop: target.offset().top - offset,
                }, speed);
              }
            };

            // If header changes size
            $(window).trigger('resize');
            clickScroll();
            return false;
          }
        });
      }
    },

    //submenu dropdowns
    // --------------------------------
    themeSubMenus: function() {
      var context = $(this);
      var $tabsPills = $('.dropdown-menu [data-toggle="tab"], .dropdown-menu [data-toggle="pill"]');

      $tabsPills.on('click', function(e) {
        event.preventDefault();
        event.stopPropagation();
        $(this).tab('show');
      });
      $tabsPills.on('shown.bs.tab', function(e) {
        var $from = $(e.relatedTarget);
        var $to = $(e.target);
        var toSelectors = $to.getSelector();
        var fromSelectors = $from.getSelector();
        var $toSelectors = $(toSelectors);
        var $fromSelectors = $(fromSelectors);

        $toSelectors.addClass('active');
        $fromSelectors.removeClass('active');
        $(document).find('[data-target="' + toSelectors + '"]').addClass('active');
        $(document).find('[data-target="' + fromSelectors + '"]').removeClass('active');
      });

      context.find('.dropdown-menu [data-toggle=dropdown]').on('click', function(event) {
        event.preventDefault();
        event.stopPropagation();

        // Toggle direct parent
        $(this).parent().toggleClass('show');
      });

      // Persistent menus
      context.find('.dropdown.dropdown-persist').on({
        "shown.bs.dropdown": function() {
          $(this).data('closable', false);
        },
        "hide.bs.dropdown": function(event) {
          temp = $(this).data('closable');
          $(this).data('closable', true);
          return temp;
        }
      });
      context.find('.dropdown.dropdown-persist .dropdown-menu').on({
        "click": function(event) {
          $(this).parent('.dropdown.dropdown-persist').data('closable', false);
        },
      });
    },

    // Gets selector from href or data-target
    getSelector: function() {
      var element = $(this);
      var selector = element.data('target');
      if (!selector || selector === '#') {
        selector = element.attr('href') || '';
      }

      try {
        var $selector = $(selector);
        return $selector.length > 0 ? selector : null;
      } catch (error) {
        return null;
      }
    },

    // calculate a height with offset
    calcHeightsOffset: function(height, offset) {
      if (typeof offset == 'number') {
        return height - offset;
      } else if (typeof offset == 'string' && $(offset).length > 0) {
        $(offset).each(function() {
          height = height - $(offset).height();
        });
      }
      return height;
    },

    // Detected IE & adds body class
    isIE: function() {
      if (document.documentMode || /Edge/.test(navigator.userAgent)) {
        return true;
      }
    },

    // Determines plugin location
    // --------------------------------
    getScriptLocation: function() {
      var location = $('body').data('plugins-localpath') || null;
      if (location) {
        return location;
      }
      return Config.PLUGINS_LOCALPATH;
    },
    
    // Delays
    // --------------------------------
    delay: function(callback, ms) {
      var timer = 0;
      clearTimeout (timer);
      timer = setTimeout(callback, ms);
    },

    // Hash from string
    // --------------------------------    
    hashCode: function(str){
      var hash = 0;
      for (var i = 0; i < str.length; i++) {
          hash = ~~(((hash << 5) - hash) + str.charCodeAt(i));
      }
      return hash;
    },

    // Load plugin
    // --------------------------------
    themeLoadPlugin: function(js, css, callback, placement) {
      // Manually loaded assets
      var manualPlugins = $('body').data('plugins-manual') || null;
      if (manualPlugins !== null) {
        if (callback && typeof(callback) === "function") {
          callback();
        }
        return;
      }

      var themeLoadPluginPath = function(url) {
        if (url.indexOf('http://') === 0 || url.indexOf('https://') === 0) {
          return url;
        }
        var location = $document.getScriptLocation();
        return location + url;
      };

      $.ajaxPrefilter("script", function(s) {
        s.crossDomain = true;
      });
      if (js.length > 0) {
        var progress = 0;
        var internalCallback = function(url) {
          // Complete
          if (++progress === js.length) {
            $.each(css, function(index, value) {
              if (Globals.LOADED_FILES[value] === value) {
                // Already loaded
                return true;
              }

              // Record file loaded
              Globals.LOADED_FILES[value] = value;
              $('head').prepend('<link href="' + themeLoadPluginPath(value) + '" rel="stylesheet" type="text/css" />');
            });

            if (callback && typeof(callback) === "function") {
              callback();
            }
          }
        };

        $.each(js, function(index, value) {
          if (Globals.LOADED_FILES[value] === value) {
            // Already loaded
            internalCallback();
            return true;
          }

          // Offline mode
          var hash = window.location.hash;
          if (hash === '#offline' && (value.indexOf('http://') === 0 || value.indexOf('https://')) === 0) {
            console.log('Offline mode: ' + value + ' loading skipped');
            return;
          }

          // Record file loaded
          Globals.LOADED_FILES[value] = value;
          if (placement === undefined) {
            var options = {
              url: themeLoadPluginPath(value),
              dataType: "script",
              success: internalCallback,
              cache: true
            };
            $.ajax(options);
          } else if (placement === 'append') {
            $('script[src*="bootstrap.min.js"]').after('<script src="' + themeLoadPluginPath(value) + '"></script>');
            internalCallback();
          } else if (placement === 'prepend') {
            $('script[src*="bootstrap.min.js"]').before('<script src="' + themeLoadPluginPath(value) + '"></script>');
            internalCallback();
          } else if (placement === 'head') {
            $('head').append('<script src="' + themeLoadPluginPath(value) + '"></script>');
            internalCallback();
          }
        });
      } else if (css.length > 0) {
        // Just CSS
        $.each(css, function(index, value) {
          if (Globals.LOADED_FILES[value] === value) {
            // Already loaded
            return true;
          }

          // Record file loaded
          Globals.LOADED_FILES[value] = value;
          $('head').prepend('<link href="' + themeLoadPluginPath(value) + '" rel="stylesheet" type="text/css" />');
        });

        if (callback && typeof(callback) === "function") {
          callback();
        }
      }
    },    
  });
})(jQuery);

// Temp fix for https://github.com/vmitsaras/js-offcanvas/commit/ab116f632a092477574d69760ecbe3d7c07b00ab#diff-7399a891b21afad97155db02f6dbd60dL142
var raf = (function(callback){
  return  window.requestAnimationFrame       ||
          window.webkitRequestAnimationFrame ||
          window.mozRequestAnimationFrame    ||
          function( callback ){
            window.setTimeout(callback, 1000 / 60);
          };
})();
var utils = {};
utils.raf = function(callback){
  raf(callback);
};
window.utils = utils;