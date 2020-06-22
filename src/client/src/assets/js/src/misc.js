// ===============================================================
// @group: Custom functionality intergration/init
// ===============================================================

(function($) {
  $.extend($.fn, {
    themeCustomScripts: function(refresh) {
      var context = $(this);
      if (typeof context === "undefined" || context === null) {
        context = $(document);
      }
      $document = $(document);
      
      // Menus need init quick, @see utils.js
      context.themeSubMenus();
      context.themeScrollMenus();      

      // ----------------------------------------------------------------
      // Hover effects
      // ----------------------------------------------------------------
      var elementsHovered = context.find('[data-hover]');
      if (elementsHovered.length > 0) {
        var initElementsHovered = function() {
          elementsHovered.each(function() {
            var $element = jQuery(this),
              animateClass = $element.data('hover'),
              animateClassOut = $element.data('hover-out'),
              animateDelay = $element.data('hover-delay') || null,
              animateDuration = $element.data('hover-duration') || null;

            // Delays & durations
            if (animateDelay !== null) {
              $element.css({
                '-webkit-animation-delay': animateDelay + 's',
                '-moz-animation-delay': animateDelay + 's',
                'animation-delay': animateDelay + 's'
              });
            }
            if (animateDuration !== null) {
              $element.css({
                '-webkit-animation-duration': animateDuration + 's',
                '-moz-animation-duration': animateDuration + 's',
                'animation-duration': animateDuration + 's'
              });
            }

            $element.hover(
              function() {
                $element.addClass('animated ' + animateClass).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
                  $element.removeClass('animated ' + animateClass).addClass('animated ' + animateClassOut);
                });
              },
              function() {}
            );
          });
        };
        $document.themeLoadPlugin([], ["https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"], function() {
          $document.isPageLoaderDone(initElementsHovered);
        });
      }

      // ----------------------------------------------------------------
      // Fullheight elements
      // ----------------------------------------------------------------
      var fullHeights = context.find('[data-toggle="full-height"]');
      if (fullHeights.length > 0) {
        var doFullHeightsOffset = function(height, offset) {
          return $document.calcHeightsOffset(height, offset);
        };

        var doFullHeights = function() {
          fullHeights.each(function() {
            var $element = $(this),
              fullHeightParent = $element.data('parent') || window,
              fullHeightOffset = $element.data('offset') || null,
              fullHeightBreakpoint = $element.data('breakpoint') || null,
              $fullHeightParent = $(fullHeightParent) || null;

            if ($fullHeightParent) {
              var fullHeightParentHeight = $fullHeightParent.height();
              var fullHeight = fullHeightParentHeight;
              if (fullHeightOffset) {
                fullHeight = doFullHeightsOffset(fullHeight, fullHeightOffset);
              }

              if (fullHeightBreakpoint) {
                if ($(window).width() <= fullHeightBreakpoint) {
                  $element.css('height', 'auto');
                } else {
                  $element.outerHeight(fullHeight);
                }
              } else {
                $element.outerHeight(fullHeight);
              }
            }
          });
        };

        doFullHeights();
        $(window).on('resize', function() {
          setTimeout(function() {
            doFullHeights();
          }, 400);
        });
      }

      // ----------------------------------------------------------------
      // Animated scroll elements
      // ----------------------------------------------------------------
      var elementsAnimated = context.find('[data-animate]');
      if (elementsAnimated.length > 0) {
        var initElementsAnimated = function() {
          // SEO hack, hide stuff when page scrolled
          $(window).on('scroll', function() {
            if (Globals.PAGE_SCROLLED !== true) {
              //$body.addClass('animates-pending');
              Globals.PAGE_SCROLLED = true;
            }
          });

          elementsAnimated.each(function() {
            var $element = $(this),
              animateClass = $element.data('animate'),
              animateInfinite = $element.data('animate-infinite') || null,
              animateDelay = $element.data('animate-delay') || null,
              animateDuration = $element.data('animate-duration') || null,
              animateOffset = $element.data('animate-offset') || '98%',
              animateInView = $element.data('animate-inview') || false,
              elementInView = $document.elementInView($element);

            // Already scroll passed so don't animate again
            if (elementInView === true && animateInView === false) {
              animateClass = null;
              $element.removeAttr('data-animate');
            }

            // Infinite
            if (animateInfinite !== null) {
              $element.addClass('infinite');
            }

            // Delays & durations
            if (animateDelay !== null) {
              $element.css({
                '-webkit-animation-delay': animateDelay + 's',
                '-moz-animation-delay': animateDelay + 's',
                'animation-delay': animateDelay + 's'
              });
            }
            if (animateDuration !== null) {
              $element.css({
                '-webkit-animation-duration': animateDuration + 's',
                '-moz-animation-duration': animateDuration + 's',
                'animation-duration': animateDuration + 's'
              });
            }

            if (animateClass !== null) {
              $element.waypoint(function() {
                $element.addClass('animated ' + animateClass).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
                  $element.addClass('animated-done');
                  $element.removeClass(animateClass);
                });
                this.destroy();
              }, {
                offset: animateOffset,
              });
            }
          });
        };

        $document.includeWaypoints(function() {
          $document.themeLoadPlugin([], ["https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"], function() {
            $document.isPageLoaderDone(initElementsAnimated);
          });
        });
      }

      // ----------------------------------------------------------------
      // Scroll state
      // ----------------------------------------------------------------
      context.find('[data-scroll="scroll-state"]').each(function() {
        var $scroll = $(this),
          $doc = $(document),
          scrollAmount = $scroll.data('scroll-amount') || $(window).outerHeight(),
          scrollAmountOut = $scroll.data('scroll-amount-out') || null, //@todo
          scrollSettings = $scroll.data('scroll-setting') || null,
          scrollEffectIn = scrollSettings !== null ? (scrollSettings.effectIn || null) : null,
          scrollEffectOut = scrollSettings !== null ? (scrollSettings.effectOut || null) : null,
          scrollEffectDelay = scrollSettings !== null ? (scrollSettings.effectDelay || null) : null,
          scrollEffectDuration = scrollSettings !== null ? (scrollSettings.effectDuration || null) : null,
          scrollBreakpoint = scrollSettings !== null ? (scrollSettings.breakpoint || null) : null,
          scrollFallbackState = scrollSettings !== null ? (scrollSettings.fallbackState || 'scroll-state-active') : null,
          scrollActive = $scroll.data('scroll-active') || true,
          effectType = 'transitions',
          $window = $(window);

        if ($scroll.hasClass('scroll-state-hidden')) {
          $scroll.data('state', 'out');
        }
        if (scrollEffectIn !== null || scrollEffectOut !== null) {
          $scroll.addClass('scroll-effect');
          $document.themeLoadPlugin([], ["https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"]);
          effectType = 'animate';
        }

        // Delays & durations
        if (scrollEffectDelay !== null) {
          $scroll.css({
            '-webkit-animation-delay': scrollEffectDelay + 's',
            '-moz-animation-delay': scrollEffectDelay + 's',
            'animation-delay': scrollEffectDelay + 's'
          });
        }
        if (scrollEffectDuration !== null) {
          $scroll.css({
            '-webkit-animation-duration': scrollEffectDuration + 's',
            '-moz-animation-duration': scrollEffectDuration + 's',
            'animation-duration': scrollEffectDuration + 's'
          });
        }

        if (scrollBreakpoint) {
          $window.on('resize', function() {
            setTimeout(function() {
              if ($window.width() <= scrollBreakpoint) {
                scrollActive = false;
                $scroll.addClass(scrollFallbackState);
                $scroll.removeClass(scrollEffectOut);
                $scroll.removeClass(scrollEffectIn);
              } else {
                scrollActive = true;
                $scroll.removeClass(scrollFallbackState);
              }
              $scroll.data('scroll-active', scrollActive);
            }, 400);
          });
        }

        $doc.scroll(function() {
          if ($scroll.data('scroll-active') === false) {
            return;
          }
          var y = $(this).scrollTop();
          var active = $scroll.data('state');
          if (y >= scrollAmount) {
            if (active === 'out') {
              $scroll.data('state', 'in');
              $scroll.addClass('scroll-state-active');
              $scroll.removeClass('scroll-state-hidden');
              if (scrollEffectOut !== null) {
                $scroll.removeClass(scrollEffectOut);
              }
              if (scrollEffectIn !== null) {
                $scroll.addClass('animated ' + scrollEffectIn);
              }
            }

          } else if (active === 'in') {
            $scroll.data('state', 'out');
            if (scrollEffectOut !== null) {
              $scroll.addClass('animated ' + scrollEffectOut);
            } else {
              $scroll.removeClass('scroll-state-active');
              $scroll.addClass('scroll-state-hidden');
            }
            if (scrollEffectIn !== null) {
              $scroll.removeClass(scrollEffectIn);
            }
          }
        });
      });

      // ----------------------------------------------------------------
      // scrollax - adjust oapcity & top on scroll
      // ----------------------------------------------------------------
      context.find('[data-scroll="scrollax"]').each(function() {
        var $scroll = $(this),
          $doc = $(document),
          $window = $(window),
          opRatio = $scroll.data('scrollax-op-ratio') || 500,
          yRatio = $scroll.data('scrollax-y-ratio') || 5;

        $doc.scroll(function() {
          var windowTop = $window.scrollTop();
          $scroll.css({
            "opacity": (opRatio === 'off' ? 1 : 1 - windowTop / opRatio),
            "transform": (yRatio === 'off' ? 0 : "translateY(" + (0 - windowTop / yRatio) + "px)"),
          });
        });
      });

      // ----------------------------------------------------------------
      // Quantity widget
      // ----------------------------------------------------------------
      context.find('[data-toggle="quantity"]').each(function() {
        var $this = $(this),
          $down = $this.find('.quantity-down'),
          $up = $this.find('.quantity-up'),
          $quantity = $this.find('.quantity');

        var toggleQuantity = function(direction) {
          var value = parseInt($quantity.val());
          if (direction === 'down') {
            value = value - 1;
          } else if (direction === 'up') {
            value = value + 1;
          }
          if (value < 0) {
            value = 0;
          }
          $quantity.val(value);
        };

        if ($quantity.length > 0) {
          $down.on('click', function() {
            toggleQuantity('down');
          });
          $up.on('click', function() {
            toggleQuantity('up');
          });
        }
      });

      // ----------------------------------------------------------------
      // Dynamic/quick CSS props
      // Example: data-css='{"height":"240px","background-position":"center center"}'
      // ----------------------------------------------------------------
      context.find('[data-css]').each(function() {
        var $this = $(this),
          currentStyles = $this.data('css') || '',
          styleProps = $this.data('css') || {},
          newStyles = {};
        if (styleProps !== null && typeof styleProps === 'object') {
          newStyles = $.extend(currentStyles, styleProps);
          $this.css(newStyles);
        }
      });

      // ----------------------------------------------------------------
      // Overlay menu
      // ----------------------------------------------------------------
      var overlayMenus = context.find('[data-toggle=overlay]');
      if (overlayMenus.length > 0) {
        overlayMenus.each(function() {
          var $this = jQuery(this),
            target = $this.data('target') || null;

          // General class for all triggers
          $this.addClass('overlay-trigger');
          if ($(target).length > 0) {
            $target = $(target);
            $this.on('click', function(e) {
              $this.toggleClass('overlay-active');
              jQuery($this.data('target')).toggleClass('overlay-active');
              jQuery('html').toggleClass('overlay-open');
              return false;
            });
          }
        });

        // Overlay dismiss links/buttons
        context.find('[data-dismiss="overlay"]').each(function() {
          var $this = jQuery(this),
            $target = $this.data('target') || '.overlay',
            $trigger = jQuery('[data-toggle="overlay"][data-target="' + $target + '"]') || null;

          // Check target overlay to close exists
          if ($($target).length > 0) {
            $target = jQuery($target);
            $this.on('click', function(e) {
              $target.removeClass('overlay-active');
              jQuery('html').removeClass('overlay-open');

              // Try to find the trigger
              if ($trigger.length > 0) {
                $trigger.removeClass('overlay-active');
              } else {
                // close all
                jQuery('[data-toggle="overlay"]').removeClass('overlay-active');
              }
              return false;
            });
          }
        });
      }

      // ----------------------------------------------------------------
      // Clickable elements
      // ----------------------------------------------------------------
      context.find('[data-url]').each(function() {
        var url = $(this).data('url');
        var parseStringUrl = function(url) {
          var a = document.createElement('a');
          a.href = url;
          return a;
        };
        var urlParse = parseStringUrl(url);
        $(this).addClass('clickable-element');

        // Hover event
        $(this).on('hover', function() {
          $(this).hover(
            function() {
              $(this).addClass("hovered");
            },
            function() {
              $(this).removeClass("hovered");
            }
          );
        });

        // Disable Links within block
        $(this).find('a').on('click', function(e) {
          if ($(this).attr('href') === urlParse.href) {
            e.preventDefault();
          }
        });

        // Click event
        $(this).on('click', function() {
          if (urlParse.host !== location.host) {
            // external
            window.open(urlParse.href, '_blank');
          } else {
            // internal
            window.location = url;
          }
        });
      });

      // ----------------------------------------------------------------
      // Search form show/hide
      // ----------------------------------------------------------------
      $searchForm = context.find('[data-toggle=search-form]');
      if ($searchForm.length > 0) {
        var $trigger = $searchForm;
        var target = $trigger.data('target');
        var $target = $(target);

        if ($target.length === 0) {
          return;
        }

        $target.addClass('collapse');
        $('[data-toggle=search-form]').click(function() {
          $target.collapse('toggle');
          $(target + ' .search').focus();
          $trigger.toggleClass('open');
          $('html').toggleClass('search-form-open');
          $(window).trigger('resize');
        });
        $('[data-toggle=search-form-close]').click(function() {
          $target.collapse('hide');
          $trigger.removeClass('open');
          $('html').removeClass('search-form-open');
          $(window).trigger('resize');
        });
      }

      // ----------------------------------------------------------------
      // colour switch - demo only
      // ----------------------------------------------------------------
      var defaultColour = $('body').data('colour-scheme') || 'green';
      var colourSchemes = context.find('.theme-colours a');
      colourSchemes.removeClass('active');
      colourSchemes.filter('.' + defaultColour).addClass('active');
      colourSchemes.click(function() {
        var $this = $(this);
        var c = $this.attr('href').replace('#', '');
        var cacheBuster = 3 * Math.floor(Math.random() * 6);
        $('.theme-colours a').removeClass('active');
        $('.theme-colours a.' + c).addClass('active');

        if (c !== defaultColour) {
          context.find('#colour-scheme').attr('href', 'assets/css/colour-' + c + '.css?x=' + cacheBuster);
        } else {
          context.find('#colour-scheme').attr('href', '#');
        }
      });

      // ----------------------------------------------------------------
      // IE placeholders
      // ----------------------------------------------------------------
      if (navigator.userAgent.toLowerCase().indexOf('msie') > -1) {
        context.find('[placeholder]').focus(function() {
          var input = jQuery(this);
          if (input.val() === input.attr('placeholder')) {
            if (this.originalType) {
              this.type = this.originalType;
              delete this.originalType;
            }
            input.val('');
            input.removeClass('placeholder');
          }
        }).blur(function() {
          var input = jQuery(this);
          if (input.val() === '') {
            input.addClass('placeholder');
            input.val(input.attr('placeholder'));
          }
        }).blur();
      }



      // ----------------------------------------------------------------
      // Bootstrap animated progressbar width
      // ----------------------------------------------------------------
      var progressBarsAnimated = context.find('[data-toggle="progress-bar-animated-progress"]');
      if (progressBarsAnimated.length > 0) {
        var initProgressBarsAnimated = function() {
          progressBarsAnimated.each(function() {
            var $progress = jQuery(this);
            var currentStyles = $progress.attr("style") || '';

            $progress.waypoint(function() {
              currentStyles += 'width: ' + $progress.attr("aria-valuenow") + '% !important;';
              $progress.attr("style", currentStyles).addClass('progress-bar-animated-progress');
              this.destroy();
            }, {
              offset: '98%'
            });
          });
        };

        $document.includeWaypoints(function() {
          progressBarsAnimated.css("width", 0);
          $document.isPageLoaderDone(initProgressBarsAnimated);
        });
      }

      // ----------------------------------------------------------------
      // Bootstrap collapse
      // ----------------------------------------------------------------
      var collapses = context.find('[data-toggle="collapse"]');
      collapses.each(function() {
        var $this = $(this);
        var target = $this.attr('href') || $this.data('target');
        var parent = $this.data('parent') || null;
        if ($(target).length > 0) {
          if ($(target).hasClass('show')) {
            $this.addClass('show');
          }
        }

        $this.on({
          'click': function() {
            $this.toggleClass('show', !$(target).hasClass('show'));
            $(window).trigger('resize');

            var $checks = $this.find('input[type="checkbox"]');
            if ($checks.length > 0) {
              $checks.prop('checked', !$(target).hasClass('show'));
            }
          }
        });
      });

      // Scroll to top of active card
      context.find('[data-accordion-focus]').on('shown.bs.collapse', function(e) {
        var headingTop = $(e.target).parent().offset().top;
        var scrollTo = $document.calcHeightsOffset(headingTop, $('#header').outerHeight());
        $('html,body').animate({
          scrollTop: scrollTo + 20
        }, 500);
      });

      var radioCollapses = context.find('[data-toggle="radio-collapse"]');
      radioCollapses.each(function(index, item) {
        var $item = $(item);
        var $target = $($item.data('target'));
        var $parent = $($item.data('parent'));
        var $radio = $item.find('input[type=radio]');
        var $radioOthers = $parent.find('input[type=radio]').not($radio);

        $radio.on('change', function() {
          if ($radio.is(':checked')) {
            $target.collapse('show');
          } else {
            $target.collapse('hide');
          }
        });

        $radio.on('click', function() {
          $radioOthers.prop('checked', false).trigger('change');
        });
      });

      // ----------------------------------------------------------------
      // Bootstrap modals onload & duration & animation
      // @see: http://v4-alpha.getbootstrap.com/components/modal/
      // ----------------------------------------------------------------
      var modalsDuration = context.find('[data-modal-duration]');
      if (modalsDuration.length > 0) {
        var $modal = modalsDuration,
          duration = $modal.data('modal-duration'),
          progressBar = $('<div class="modal-progress"></div>');

        $modal.find('.modal-content').append(progressBar);

        // Actual durations
        $modal.on('show.bs.modal', function(e) {
          var i = 2;
          var durationProgress = setInterval(function() {
            progressBar.width(i++ + '%');
          }, duration / 100);

          setTimeout(function() {
            $modal.modal('hide');
            clearInterval(durationProgress);
          }, duration);
        });
      }

      var modalsOnload = context.find('[data-toggle="modal-onload"]');
      if (modalsOnload.length > 0) {
        modalsOnload.on('shown.bs.modal', function() {
          $(this).data('modal-shown', true);
        });

        modalsOnload.each(function() {
          var $modal = $(this),
            delay = $modal.data('modal-delay') || null,
            force = $modal.data('modal-force') || false; // Open it again ever if opened once before
          var startModal = function($modal) {
            var shown = $modal.data('modal-shown') || false;

            //if (shown === false || shown && force) {
            $modal.modal();
            //}
          };

          // Delay modal opening
          if (delay !== null) {
            setTimeout(function() {
              startModal($modal);
            }, delay);
          } else {
            // No delay trigger direct
            startModal($modal);
          }
        });
      }

      // Inline modals ie. no fixed backdrop
      var modalsHideBd = context.find('[data-backdrop=false]');
      var $body = $('body');
      modalsHideBd.on('show.bs.modal', function(e) {
        $(this).data('bs.modal')._config.backdrop = false;
        $body.addClass('modal-no-backdrop');
      });
      modalsHideBd.on('hidden.bs.modal', function(e) {
        $body.removeClass('modal-no-backdrop');
      });

      // Animate.css modal integration
      function modalAnimate($m, s, e) {
        var animateIn = $m.data('modal-animate-in') || 'fadeIn',
          animateOut = $m.data('modal-animate-out') || 'fadeOut',
          animateAdd = animateIn,
          animateRemove = animateOut;

        if (s == 'out') {
          animateAdd = animateOut;
          animateRemove = animateIn;
        }

        $m.removeClass(animateRemove);
        $m.addClass(animateAdd + '  animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
          $m.removeClass(animateAdd + '  animated');
          if (s == 'out') {
            $m.removeClass('modal-animate-closing');
            $body.removeClass('modal-animate');
          }
        });
      }
      var $modalsAnimate = context.find('[data-modal-animate-in], [data-modal-animate-out]');
      if ($modalsAnimate.length > 0) {
        var $modalBackdrop = $('<div></div>').addClass('modal-animate-backdrop');
        $modalBackdrop.appendTo($body);
        $modalsAnimate.on('click.backdropDismiss', function(e) {
          var $m = $(e.target).hasClass('modal') ? $(e.target) : null;
          if ($m) {
            $m.modal('hide');
          }
        });

        $modalsAnimate.on('show.bs.modal', function(e) {
          var $m = $(this);
          var backdrop = $m.data('bs.modal')._config.backdrop;
          $body.addClass('modal-animate');
          $m.data('bs.modal')._config.backdrop = false;

          if (backdrop) {
            $modalBackdrop.addClass('show');
          }
          modalAnimate($m, 'in', e);
        }).on('hide.bs.modal', function(e) {
          var $m = $(this);
          $m.addClass('modal-animate-closing');
          $modalBackdrop.removeClass('show');
          modalAnimate($m, 'out', e);
        });
      }

      // ----------------------------------------------------------------
      // Bootstrap tooltip
      // @see: http://getbootstrap.com/javascript/#tooltips
      // ----------------------------------------------------------------
      // invoke by adding data-toggle="tooltip" to a tags (this makes it validate)
      if ($document.tooltip) {
        context.find('[data-toggle="tooltip"]').tooltip();
      }

      // ----------------------------------------------------------------  
      // Bootstrap popover
      // @see: http://getbootstrap.com/javascript/#popovers
      // ----------------------------------------------------------------
      // invoke by adding data-toggle="popover" to a tags (this makes it validate)
      if ($document.popover) {
        context.find('[data-toggle="popover"]').popover();
      }



      // ----------------------------------------------------------------
      // allow any page element to set page class
      // ----------------------------------------------------------------
      context.find('[data-page-class]').each(function() {
        context.find('html').addClass(jQuery(this).data('page-class'));
      });

      // ----------------------------------------------------------------
      // Detect Bootstrap fixed header
      // @see: http://getbootstrap.com/components/#navbar-fixed-top
      // ----------------------------------------------------------------
      if (context.find('.navbar-fixed-top').length > 0) {
        context.find('html').addClass('has-navbar-fixed-top');
      }

      // ----------------------------------------------------------------
      // simple class toggles
      // ----------------------------------------------------------------  
      context.find('[data-toggle="class"]').each(function() {
        var $this = $(this);
        var target = $this.data('target');
        var $target = $(target);
        var toggleClass = $this.data('toggle-class') || 'show';
        var toggleTrigger = $this.data('toggle-trigger') || 'click';
        var toggleAction = $this.data('toggle-action') || 'toggle';
        var toggleCallback = function(t, c, a) {
          if (typeof c === 'object') {
            $.each(c, function(i, name) {
              if (a === 'remove') {
                t.removeClass(name);
              } else if (a === 'add') {
                t.addClass(name);
              } else {
                t.toggleClass(name);
              }
            });
          } else {
            if (a === 'remove') {
              t.removeClass(c);
            } else if (a === 'add') {
              t.addClass(c);
            } else {
              t.toggleClass(c);
            }
          }
        };

        if (typeof target === 'object') {
          $.each(target, function(selector, data) {
            var _toggleTrigger = data.toggleTrigger || toggleTrigger;
            var _toggleActions = data.actions || toggleAction;

            $this.on(_toggleTrigger, function() {
              if (typeof _toggleActions === 'object') {
                $.each(_toggleActions, function(action, classes) {
                  toggleCallback($(selector), classes, action);
                });
              } else {
                toggleCallback($(selector), _toggleClass, _toggleAction);
              }

              return false;
            });
          });
        } else {
          if ($target.length === 0) {
            return;
          }
          $this.on(toggleTrigger, function() {
            toggleCallback($target, toggleClass, toggleAction);
            return false;
          });
        }
      });

      // ----------------------------------------------------------------
      // Simple class togglers
      // <div id="target"></div>
      // <a href="#target" data-target="#target" data-class="something-something">Toggle</a>
      // ----------------------------------------------------------------      
      $("[data-toggle='toggle']").click(function() {
        var $this = $(this);
        var selector = $this.getSelector();
        var toggleClass = $this.data("class");
        $(selector).toggleClass(toggleClass);
      });

      // ----------------------------------------------------------------
      // show hide for hidden header
      // ----------------------------------------------------------------
      context.find('[data-toggle=show-hide]').each(function() {
        var $this = jQuery(this);
        var target = $this.attr('data-target');
        var $target = $(target);
        var state = 'show'; //open or hide
        var targetState = $this.attr('data-target-state');
        var callback = $this.attr('data-callback');

        if ($target.length === 0) {
          return;
        }

        if (state === 'show') {
          // Close by default
          $target.addClass('collapse');
        }

        $this.click(function() {
          //allows trigger link to say target is open & should be closed
          if (typeof targetState !== 'undefined' && targetState !== false) {
            state = targetState;
          }
          if (state === undefined) {
            state = 'show';
          }
          if (!$target.hasClass('show')) {
            // About to open
            $this.addClass('show');
          } else {
            $this.removeClass('show');
          }

          $target.collapse('toggle');

          if (callback && typeof(callback) === "function") {
            callback();
          }
        });
      });

      // Clones DOM elements
      // --------------------------------
      context.find('[data-clone]').each(function() {
        var $this = $(this);
        var clone = $this.data('clone') || null; // @todo - allow multiple clones
        var cloneTo = $this.data('clone-to') || null;
        var $cloneFrom, $cloneTo;
        var placement = $this.data('clone-placement') || 'after';
        var classesRemove = $this.data('clone-classes-remove') || [];
        var classesAdd = $this.data('clone-classes-add') || [];

        // Clone to
        $cloneTo = $this;
        if (cloneTo !== null) {
          $cloneTo = $(cloneTo);
          $cloneTo = $cloneTo.length() > 0 ? $cloneTo : $this;
        }

        // Clone from
        $clone = $(clone);
        $clone = $clone.length > 0 ? $clone : null;
        if ($clone === null) {
          return;
        }

        // Manipulate clone
        $clone = $clone.clone();
        $clone.addClass('cloned-element');

        // Remove elements


        // Classes
        if (classesRemove !== null && $.isArray(classesRemove)) {
          $.each(classesRemove, function(k, c) {
            $clone.removeClass(c);
          });
        }
        if (classesAdd !== null && $.isArray(classesAdd)) {
          $.each(classesAdd, function(k, c) {
            $clone.addClass(c);
          });
        }

        // Placement
        if (placement === 'before') {
          $clone.prependTo($this);
        } else {
          $clone.appendTo($this);
        }
      });
    },
  });
})(jQuery);

