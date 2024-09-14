// ignore_for_file: file_names

import 'package:flutter/material.dart';

class AnnonceCard extends StatelessWidget {
  final String photoProfil;
  final String username;
  final String cours;
  final String datePublication;
  final String description;

  const AnnonceCard({
    required this.photoProfil,
    required this.username,
    required this.cours,
    required this.datePublication,
    required this.description,
  });

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(0), // Coins carrés
        side: BorderSide(
            color: Color.fromARGB(50, 173, 171, 170),
            width: 2), // Bordure grise
      ),
      margin: EdgeInsets.only(
          bottom: screenWidth * 0.016,
          left: screenWidth * 0.01,
          right: screenWidth * 0.01),
      surfaceTintColor: Colors.white,
      shadowColor: Colors.white,
      color: Colors.white,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(top: 6.0, left: 8.0, right: 8.0),
            child: Row(
              children: <Widget>[
                CircleAvatar(
                  backgroundImage: AssetImage(photoProfil),
                  radius: screenWidth * 0.06,
                ),
                const SizedBox(width: 8.0),
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Padding(
                        padding: const EdgeInsets.only(top: 2),
                        child: Text(
                          username,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: screenWidth * 0.03),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(left: 1, top: 5),
                        child: Text(
                          cours,
                          style: TextStyle(fontSize: screenWidth * 0.025),
                        ),
                      )
                    ],
                  ),
                ),
                IconButton(
                  icon: const Icon(Icons.more_horiz),
                  onPressed: () {},
                ),
              ],
            ),
          ),
          Padding(
            padding: EdgeInsets.only(
                top: 15,
                bottom: 10,
                left: screenWidth * 0.01,
                right: screenWidth * 0.01),
            child: Text(
              description,
              style: TextStyle(fontSize: screenWidth * 0.04),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Text(
              datePublication,
              style:
                  TextStyle(color: Colors.grey, fontSize: screenWidth * 0.038),
            ),
          ),
        ],
      ),
    );
  }
}
