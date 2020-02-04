// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginOffCanvas = function(context) {
  // ----------------------------------------------------------------
  // Plugin: Off Canvas
  // data-toggle=off-canvas must be present in HTML
  // @see: http://offcanvas.vasilis.co/index.html
  // ----------------------------------------------------------------
  var $offCanvass = context.find('[data-toggle="jpanel-menu"],[data-toggle="off-canvas"]');
  if ($offCanvass.length > 0) {
    var themePluginOffCanvasInit = function() {
      var $bodyWrap = $('<div class="c-offcanvas-content-wrap"></div>');
      var $sideBarWrap = $('<aside class="js-offcanvas"></aisde>');
      var $excludedContent = $('.jpanel-menu-exclude,.js-off-canvas-exclude,.modal,.colour-switcher') || null;
      $('body').wrapInner($bodyWrap);
      
      // Move any excluded content outside wrapper (.c-offcanvas-content-wrap)
      if ($excludedContent) {
        $excludedContent.appendTo('body');
      }

      var defaultSettings = {
        modifiers: 'right,reveal',
        resize: true,
        modal: true,
        cloneTarget: true,
        modalClassExtras: "overlay overlay-dark overlay-op-4 overlay-close-cursor",
        targetClassExtras: ""
      };
  
      var enforcedSettings = {
        modalClass: "c-offcanvas-bg",
        contentClass: 'c-offcanvas-content-wrap',
        onInit: function() {
          var $oc = $(this);
          var dataOffcanvas = $oc.data('offcanvas-component');
          var settings = dataOffcanvas.options;

          if (settings.modalClassExtras && settings.modal) {
            // Add extra classes
            dataOffcanvas.$modal.addClass(settings.modalClassExtras);
          }
          if (settings.targetClassExtras) {
            // Add extra classes
            dataOffcanvas.$element.addClass(settings.targetClassExtras);
          }
          if (settings.autoOpen) {
            // Auto open
            dataOffcanvas.open();
          }
        },
        onClose: function() {
          // Toggle bug, reopen target
          var targetReopen = $(document).data('target-reopen') || false;
          if (targetReopen) {
            setTimeout(function() {
              $(document).data('target-reopen', false);
              targetReopen.data('offcanvas-component').open();
            }, 500); 
          }
        }
      }
 
      $offCanvass.each(function() {
        var $offCanvas = $(this);
        var uniqid = Date.now();
        var target = $offCanvas.data('target');
        var $target = target ? $(target) : null;
        var customSettings = $offCanvas.data('settings') || {};
        var settings = $.extend({}, defaultSettings, customSettings, enforcedSettings);
        var targetID;
        var targetClasses = 'js-offcanvas-target js-offcanvas-target-'+ uniqid;

        $offCanvas.addClass('js-offcanvas-trigger js-offcanvas-trigger-'+ uniqid);
        settings.triggerButton = '.js-offcanvas-trigger-'+ uniqid;  

        if ($target) {
          // Target must have ID
          if (typeof $target.attr('id') === 'undefined') {
            targetID = 'js-offcanvas-target-'+ uniqid;
          }
          else {
            targetID = $target.attr('id');
          }
          
          if (settings.cloneTarget) {
            // clone target not move
            targetID += '-clone';
            var $targetClone = $target.clone(true);
            $sideBarWrap.addClass(targetClasses +' js-offcanvas-target-clone').attr('id', targetID).append($targetClone).appendTo('body');
            $target = $sideBarWrap;
          }
          else {
            // Move outside of wrap
            $target.hide();
            $target.attr('id', targetID).addClass(targetClasses).appendTo('body').show();
          }
          
          $offCanvas.data('offcanvas-trigger', targetID);
          $offCanvas.data('offcanvas-target', $target);

          settings.offcanvas = targetID;
          settings.$trigger = $offCanvas;
          $offCanvas.data('js-offcanvas-settings', settings);
          $target.offcanvas(settings);
          
          // Toggle bug if already open
          $offCanvas.off("click");
          $offCanvas.on("click", function(e) { 
            // Item already open, close it & init opening again
            var $this = $(this);
            var $target = $this.data('offcanvas-target');
            var $targets = $('.js-offcanvas-target');
            var targetOpen = $target.hasClass('is-open') || false;
            var targetDataOffcanvas = $target.data('offcanvas-component');
            
            if ($('body').hasClass('has-offcanvas--visible')) {
              // Already open
              if (!targetOpen) {
                $(document).data('target-reopen', $target);
              }
              $targets.each(function() {
                var $that = $(this);
                var tDataOffcanvas = $that.data('offcanvas-component');
                tDataOffcanvas.close();
              });
              return false;
            }
            else {
              // Fire defaults
              targetDataOffcanvas.open();
            }
          });
        }
      });     
    };
    
    $document.themeLoadPlugin(
      ["https://unpkg.com/js-offcanvas/dist/_js/js-offcanvas.pkgd.js"],
      ["https://unpkg.com/js-offcanvas/dist/_css/prefixed/js-offcanvas.css", "plugin-css/plugin-offcanvas.min.css"],
      themePluginOffCanvasInit
    );
  }
};


