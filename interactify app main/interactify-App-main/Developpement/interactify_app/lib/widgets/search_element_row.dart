import 'package:flutter/material.dart';
import 'package:interactify_app/models/Users.dart';
import 'package:interactify_app/models/utilisateur.dart';

class SearchElement extends StatelessWidget {
  final Utilisateur user;

  SearchElement({super.key, required this.user});



  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 10),
          child: Row(
            children: [
              CircleAvatar(
                radius: 25,
                backgroundImage:
                    AssetImage(user.photoProfil ?? "assets/images/user.png"),
              ),
              const SizedBox(width: 16),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    user.username,
                    style: TextStyle(
                      fontSize: 20,
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
        Container(
          width: double.infinity,
          height: 2,
          color: const Color.fromARGB(255, 190, 216, 238),
        ),
      ],
    );
  }
}
