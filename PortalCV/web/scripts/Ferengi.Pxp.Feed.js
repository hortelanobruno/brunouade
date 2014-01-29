if(typeof Ferengi == "undefined") {
	var Ferengi = {
		Version: "1.0"
	};
	Ferengi.namespace = function() {
		var a = arguments,
			o = null,
			i, j, d;
		for(i=0; i<a.length; ++i) {
			d = a[i].split(".");
			o = Ferengi;
			for(j=(d[0]=="Ferengi")?1:0; j<d.length; ++j) {
				o[d[j]]=o[d[j]] || {};
				o=o[d[j]];
			}
		}
		return o;
	};
};
Ferengi.namespace("Pxp");
// Creating a class
Ferengi.Pxp.Feed = new Class({
	Implements: Options,
	options: {
		Id:"",
		Title:""
	},
	initialize: function(options) {
		this.setOptions(options);
		// initialize commands goes here
	}

});
Ferengi.Pxp.Feed.implement({
    write: function () {
        var Id = this.options.Id;
        document.write("<div id='feed" + Id + "'></div>")
        this.load('feed' + Id);
    }
    , load: function (divId) {
        var Id = this.options.Id;
        var Title = this.options.Title;
        var DataFactoryId = this.options.DataFactoryId;
        var url = '/feeds.aspx?Id=' + Id + "&DataFactoryId=" + DataFactoryId;
        if (Title != "") {
            url += '&Title=' + Title;
        }
        var myAjaxFeed = new Request({
            url: url,
            method: 'get',
            onSuccess: function (html) {
                var el = $(this.currentDivId);


                var parent = null;
                var isInsideTable = false;
                if (el.parentNode.parentNode.parentNode.parentNode.nodeName.toLowerCase() == "tr") {
                    isInsideTable = true;
                    parent = el.parentNode.parentNode.parentNode.parentNode; //tr
                }
                if (el.parentNode.parentNode.parentNode.nodeName.toLowerCase() == "tr") {
                    isInsideTable = true;
                    parent = el.parentNode.parentNode.parentNode.parentNode.parentNode; //table
                }

                if (!isInsideTable || html.contains('img')) {
                    el.set('html', html);
                    if ($defined(TipsInit)) {
                        TipsInit(el)
                    }
                } else {
                    parent.parentNode.removeChild(parent);
                }

            },
            evalScripts: true
        });
        myAjaxFeed.send();
        myAjaxFeed.currentDivId = divId;


    }
});

window.addEvent("domready", function() {
    var oo = $$('.SlbfFeed');
    for (var i = 0; i < oo.length; i++) {
        var o = oo[i];

        var Params = "";
        var SlbfFeedId = o.getAttribute('SlbfFeed')
        var DataFactoryId = o.getAttribute('DataFactoryId')

        var f = new Ferengi.Pxp.Feed({ Id: SlbfFeedId, DataFactoryId: DataFactoryId })
        f.load(o);
    }
});