package edu.depaul.models;

public class Response {
    private ResponseStatus _status;

    public Response(ResponseStatus status){
        _status = status;
    }

    public ResponseStatus getStatus(){
        return _status;
    }
}
