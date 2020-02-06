// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginFixTo = function(context) {
  // ----------------------------------------------------------------
  // Plugin: fixto(sticky navbar)
  // @see: https://bbarakaci.github.io/fixto/
  // ----------------------------------------------------------------
  var $fixTos = context.find('[data-toggle=clingify], [data-toggle=sticky]');
  if ($fixTos.length > 0) {
    var themePluginFixToInit = function() {
      var stickySetSettings = function(sticky) {
        var stickySettings = {};
        stickySettings = sticky.data('settings') || {};
        stickySettings.className = 'is-sticky';
        stickySettings.useNativeSticky = false;
        sticky.data('stickSettings', stickySettings);
        return stickySettings;
      };

    /*! Computed Style - v0.1.0 - 2012-07-19
    * https://github.com/bbarakaci/computed-style
    * Copyright (c) 2012 Burak Barakaci; Licensed MIT */
    var computedStyle = (function() {
        var computedStyle = {
            getAll : function(element){
                return document.defaultView.getComputedStyle(element);
            },
            get : function(element, name){
                return this.getAll(element)[name];
            },
            toFloat : function(value){
                return parseFloat(value, 10) || 0;
            },
            getFloat : function(element,name){
                return this.toFloat(this.get(element, name));
            },
            _getAllCurrentStyle : function(element) {
                return element.currentStyle;
            }
        };

        if (document.documentElement.currentStyle) {
            computedStyle.getAll = computedStyle._getAllCurrentStyle;
        }

        return computedStyle;

    }());      
      
      // Overwrite fixTo function
      var fixToProto = fixto.FixToContainer.prototype;
      $.extend(fixto.FixToContainer.prototype, {        
        _shouldFix: function() {
          var _sticky = this._$child;
          var _stickySettings = _sticky.data('stickSettings');
          var _stickyTopOffset = _sticky.topOffset || 0;
          var _stickyPersist = _stickySettings.persist || false;
          var _sticking = _sticky.data('sticking') || false;
          var _unsticking = _sticky.data('unsticking') || false;
          
          if (_stickyPersist) {
            return true;
          }
          
          // Already at the top
          var _scroll = $(window).scrollTop();
          var _isStickyHeader = _sticky.find('.header');
          if (_isStickyHeader && _scroll === 0 && _unsticking === false) {
            return false;
          }
          
          var _top = (this._fullOffset('offsetTop', this.child) - this.options.top - this._mindtop()) + _stickyTopOffset;
          if (this._scrollTop < this._parentBottom && this._scrollTop > _top) {
            if (this.options.mindViewport && !this._isViewportAvailable() && _unsticking === false) {
              return false;
            }
            
            if (_sticking === false) {
              return true;
            }
          }
        }
      });
      
      var stickyStart = function(sticky, state) {
        var stickySettings = stickySetSettings(sticky);
        var stickyParent = stickySettings.parent || 'body';
        var stickyPersist = stickySettings.persist || false;
        var stickyDelay = stickySettings.delay || false;
        var stickyBreakpoint = stickySettings.breakpoint || false;
        var isStickyHeader = sticky.find('.header') || false;
        var $window = $(window);
        state = state || 'init';
        
        if (isStickyHeader) {
          sticky.wrapInner('<div class="sticky-inner"></div>');
        }

        // Init
        sticky.addClass('sticky').fixTo(stickyParent, stickySettings);

        // Sticky from the start - @todo
        if (stickyPersist) {
          stickySetPersist(sticky, stickySettings);
        }

        $window.on('scroll', function() {
          // Make header unsticky when at the top
          var scroll = $(window).scrollTop();
          if (isStickyHeader && scroll === 0) {
            if (sticky.data('fixto-instance') !== '') {
              sticky.fixTo('refresh');
            }
          }
        });

        $window.on('resize', function() {
          setTimeout(function() {
            if (stickyBreakpoint) {
              if ($(window).width() <= stickyBreakpoint) {
                sticky.fixTo('destroy');
                sticky.data('fixto-instance', '');
                sticky.removeClass('is-sticky-persist');
              } else {
                if (sticky.data('fixto-instance') === '') {
                  sticky.addClass('sticky').fixTo(stickyParent, sticky.data('stickSettings'));
                }
              }
            }

            if (stickyPersist) {
              stickySetPersist(sticky, stickySettings);
            }
          }, 400);
        });

        $window.on('orientationchange', function() {
          if (isStickyHeader) {
            if (sticky.data('fixto-instance') !== '') {
              setTimeout(function() {
                sticky.fixTo('refresh');
              }, 400);
            }
          }
        });
      };

      var stickySetPersist = function(sticky, stickySettings) {
        sticky.addClass('is-sticky-persist');
        var stickyInstance = sticky.data('fixto-instance') || null;
        
        var persistTop = sticky.prev()[0].getBoundingClientRect().top  + $(window).scrollTop();
        if (persistTop < 0) {
          persistTop = 0;
        }
        
        if (stickySettings.mind !== '') {
          $(stickySettings.mind).each(function(key, value) {
            var $this = $(value);
            if ($this.length > 0) {
              persistTop -= $this.outerHeight();
            }
          });
        }
        if (stickyInstance !== null) {
          stickyInstance.fixed = true;
          sticky.data('fixto-instance', stickyInstance);
          sticky.fixTo('setOptions', {
            top: persistTop
          });
        } else {
          sticky.attr('style', 'top: auto;');
        }
      };

      $fixTos.each(function(i) {
        stickyStart($(this));
      });
    };
    $document.themeLoadPlugin(
      ["https://cdnjs.cloudflare.com/ajax/libs/fixto/0.5.0/fixto.js"],
      ["plugin-css/plugin-sticky-classes.min.css"],
      themePluginFixToInit
    );
  }
};


