var casper = require('casper').create();

casper.test.begin('baidu test', function suite(test) {
	casper.start("http://www.baidu.com/",
			function() {
				test.assertTitle("百度一下，你就知道xxx",
						"baidu's title");
			});
	
	casper.run(function() {
		test.done();
		this.exit(casper.test.suiteResults.countFailed());
	});
});


