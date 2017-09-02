module.exports = function(app, fs)
{

    app.post('/getAlarm/:time:', function(req, res){

        var result = {  };
        // CHECK REQ VALIDITY
        if(!req.body["time"] || !req.body["hour"]){
            result["success"] = 0;
            result["error"] = "invalid request";
            res.json(result);
            return;
        }



}