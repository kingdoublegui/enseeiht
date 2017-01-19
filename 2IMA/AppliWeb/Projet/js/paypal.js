function httpRequest(req, callback){
	var data = null;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState == 4 && callback){
            callback(xmlHttp);
		}
    }
	xmlHttp.open(req.type, req.url, true);
	if(req.type === "POST"){
		// Params parsing
		data = [];
		for(var p in req.params){
			data.push(p + "=" + req.params[p])
		}
		data = data.join("&");
		// Headers
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	}
    xmlHttp.send(data);
}

paypal.Button.render({

        env: 'sandbox', // Specify 'sandbox' for the test environment
		locale: 'fr_FR',

        style: {
            size: 'small',
            color: 'orange',
            shape: 'pill'
        },

        client: {
            sandbox:    'ASA1dJcXuxS4sT99xnz54ySlvqb_Wcv4pwN8iebJyx8UtyhI1_8jPN2G83j5NykZBHnlpYp7WT3QnOKa',
            production: 'AX9QDCtPBGMff_yeTjCdHPfLSrL38rZnA1rYWRxKYjhvf4qjQn4C4Nu_aBCg6PD46H2tBVTonETkNlv_'
        },


        payment: function() {
            // Set up the payment here, when the buyer clicks on the button
        	var env    = this.props.env;
            var client = this.props.client;


            return paypal.rest.payment.create(env, client, {
                transactions: [
                    {
                        amount: { total: '1.00', currency: 'EUR' },
                		description : 'Upgrade your account and become a premium member which will give you a lot of advantages.',
                		item_list: {
                		    "items": [
                		      {
                		      "name": "Premium Acces",
                		      "description": "Be a premium member",
                		      "quantity": "1",
                		      "price": "1",
                		      "tax": "0.00",
                		      "sku": "1",
                		      "currency": "EUR"
                		      }
                		    ]
                		},
                    }
                ],
        		note_to_payer : 'Upgrade your account and become a premium member which will give you a lot of advantages.'
            });
        },

        onAuthorize: function(data, actions) {
            // Execute the payment here, when the buyer approves the transaction
        	httpRequest(
        			{
        				//Ajouter en java la date de l'ajout
        				url: "users?action=premium",
        				type: "POST",
        				params: {
        					mois : 1
        				}
        			},
					getUserInfo
        		);
       },

       onError: function(err) {
           // Show an error page here, when an error occurs
           //rediriger vers la page de base du jeu
    	   alert('erreur');
       },
       onCancel : function(data, actions){
    	   alert('cancel');
       }

    }, '#paypal-button');
