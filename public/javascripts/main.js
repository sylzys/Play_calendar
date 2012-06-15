$(document).ready(
		function() {
			// day click
			var cur_date = '';
			$('.day').click(function() {
				var months = {
					"janvier" : "01",
					"février" : "02",
					"mars" : "03",
					"avril" : "04",
					"mai" : "05",
					"juin" : "06",
					"juillet" : "07",
					"août" : "08",
					"septembre" : "09",
					"octobre" : "10",
					"novembre" : "11",
					"décembre" : "12"
				};
				var date = $(this).attr('id').split("/");
				cur_date = months[date[1]] + '/' + date[0];
				get_query();
			});

			$('#rdv').delegate('.add_rdv', 'click', function(event) {

				$('#my_rdv').hide('slide', {
					direction : 'left'
				}, 1000);
				$('#new_rdv').show('slide', {
					direction : 'right'
				}, 1000);
			});
			$('#rdv').delegate(
					'.val_rdv',
					'click',
					function(event) {
						$.ajax({
							url : '/add_appointement/'
									+ $('#date_rdv').val().replace(/\//g, '-')
									+ '&' + $('#descr_rdv').val() + '&'
									+ $('#type_rdv').val() + '&'
									+ $('#heure_rdv').val() + '&'
									+ $('#minute_rdv').val(),
							type : 'GET',
							success : function(data) {
								get_query();
								$('#my_rdv').show('slide', {
									direction : 'left'
								}, 1000);
								$('#new_rdv').hide('slide', {
									direction : 'right'
								}, 1000);

							},
							error : function(data) {
								alert(data.responseText);
							}
						});
					});

			$('#rdv').delegate('#date_rdv', 'focus', function(event) {
				var tmp = cur_date.split('/');
				$(this).datepicker({
					minDate : new Date(2012, tmp[0] - 1, tmp[1])
				});
			});

			$('#rdv').delegate('.expand', 'click', function(event) {
				if ($(this).attr('src') == "public/images/down.png") {
					$(this).parent().next().show('slide', {
						direction : 'down'
					}, 1000);
					$(this).attr('src', "public/images/up.png");
				} else {
					$(this).parent().next().hide('slide', {
						direction : 'up'
					}, 1000);
					$(this).attr('src', "public/images/down.png");
				}
				//console.log("plop " + $(this).parent().next().html());
			});
			function get_query() {

				var date = cur_date.split('/');
				$.ajax({
					url : '/get_appointement/' + 2012 + '-' + date[0] + '-'
							+ date[1],
					type : 'GET',
					dataType : 'html',
					success : function(data) {
						// alert(data);
						$('#rdv').html(data);
						$('#rdv').dialog(
								{
									title : 'Rendez-vous du ' + date[1] + '/'
											+ date[0] + '/2012',
									width : 450,
									height : 300,
									position : [ "center", "center" ],
									modal : true
								});

					},
					error : function(data) {
						alert(data.responseText);
					}
				});
			}
		});