// We load the Booked model
var Booked = require('../models/booked');
var Location = require('../models/location');
var User = require('../models/user');
var mongoose = require('../node_modules/mongoose');

// **********************************
// POST Booked Rides
// **********************************
// Create endpoint /api/booked for POSTS
exports.postBooked = function(req, res) {
    // Create a new instance of the Booked model
    var booked = new Booked();
    // Set the booking properties that came from the POST data
    console.log(req.body.location_lat);
    console.log(req.body.location_long);
    console.log(req.body.destination_lat);
    console.log(req.body.destination_long);
    console.log(req.body.numPeople);
    console.log(req.body.rideWithRandoms);

    booked.location_lat = req.body.location_lat;
    booked.location_long = req.body.location_long;
    booked.destination_lat = req.body.destination_lat;
    booked.destination_long = req.body.destination_long;
    booked.numPeople = req.body.numPeople;
    booked.rideWithRandoms = req.body.rideWithRandoms;
    booked.username = req.body.username;

    //passport will automatically set the user in req.user
    // booked.userId = req.user._id;

    // Save the Booking and check for errors
    booked.save(function(err) {
        if (err) {
            res.send(err);
            return;
        }

        res.json({
            success: 'Ride booked successfully',
            data: booked
        });
    });
};

// **********************************
// GET Booked Rides
// **********************************
// Create endpoint /api/booked for GET
exports.getBooked = function(req, res) {
    // Use the booked model to find all bookings
    // from particular user with their username
    console.log("start getBooked (get bookings)");
    Booked.find({}).lean().exec(function(err, bookeds) {
        if (err) {
            res.send(err);
            return;
        }

        console.log("Booked Location Lat: " + bookeds.location_lat);
        console.log("Booked Location Long: " + bookeds.location_long);


        var counter = 0;
        var l = bookeds.length;
        res.json(bookeds);


        // //We create a closure to have access to the bookings
        // var closure = function(booked) {

        //     return function(err, booked) {

        //         counter++;

        //         if (err) {
        //             res.send(err);
        //             console.log("Booked Schema Errors: " + err);
        //         }


        //         //booked.username = user.username;

        //         //when all the users have been set
        //         if (counter === l) {
        //             //respond
        //             res.json(booked);
        //             console.log("Booked Schema inside counter: " + err);
        //             console.log("Value of booked after users are set: " + booked);
        //             return;
        //         }
        //     };
        // };

        // //We iterate through all bookings to find their associated
        // //username.
        // for (var i = 0; i < l; i++) {
        //     User.findById(bookeds[i].username, closure(bookeds[i]));
        // }

    });
};