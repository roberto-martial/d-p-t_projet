import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:interactify_app/Pages/setting_page.dart';
import 'package:interactify_app/models/users.dart';
import 'package:interactify_app/models/publication.dart';
import 'package:interactify_app/models/utilisateur.dart';
import 'package:interactify_app/widgets/nav_bar.dart';
import 'package:interactify_app/widgets/publication_card.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

class ProfilePage extends StatelessWidget {
  static const routeName = "/profile/user";

  ProfilePage({super.key});

  final List<PublicationCard> publications = [];

  Users utilisateur1 = Users(
      id: "",
      photo: "assets/images/marx.png",
      promotion: "X2025",
      username: "SyapiDYG",
      email: '');

  late Publication publication1;

  @override
  Widget build(BuildContext context) {
    final localizations = AppLocalizations.of(context)!;

    publication1 = Publication(
        id: "1",
        datePublication: Timestamp.now(),
        description:
            "yyyyyyyyyy hhhhhhhhhhhhhhh hhhhhhhhhhhhhh ddddddddddddd vvvvvvvvvvvvvvvv bbbbbbbbbbbbbbb nnnnnnnnnnnnnnnn ggggggggggggggggggg ooooooooooooooooooooooo",
        image: "assets/images/Rectangle 4750.png",
        commentaires: null,
        likes: null,
        utilisateurId: "F9jiHlFRHDPOxMiCW1p6c1HINvH3");

    PublicationCard card = PublicationCard(publication: publication1);
    publications.add(card);
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        elevation: 0,
        automaticallyImplyLeading: false,
        title: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            const Text(
              'Jerome Bell',
              style: TextStyle(
                  color: Colors.black,
                  fontSize: 24,
                  fontWeight: FontWeight.w600),
            ),
            IconButton(
              icon: const Icon(Icons.settings),
              onPressed: () {
                Navigator.pushNamed(context, SettingsPage.routeName);
              },
            ),
          ],
        ),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            CircleAvatar(
              radius: 50,
              backgroundImage: AssetImage("assets/images/marx.png"),
            ),
            Padding(
              padding: const EdgeInsets.all(14),
              child: Text("@Name Surname",
                  style: TextStyle(fontWeight: FontWeight.w600, fontSize: 20)),
            ),
            Padding(
              padding: const EdgeInsets.all(8),
              child: Row(
                children: [
                  Expanded(
                    child: Column(
                      children: [
                        Text(
                          "12",
                          style: TextStyle(
                              fontWeight: FontWeight.w700, fontSize: 20),
                        ),
                        Text(localizations.posts,
                            style: TextStyle(fontWeight: FontWeight.w500))
                      ],
                    ),
                  ),
                  Expanded(
                    child: Column(
                      children: [
                        Text(
                          "X2025",
                          style: TextStyle(
                              fontWeight: FontWeight.w700, fontSize: 20),
                        ),
                        Text("Promotion",
                            style: TextStyle(fontWeight: FontWeight.w500))
                      ],
                    ),
                  ),
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text(
                localizations.profileMessage,
                textAlign: TextAlign.center,
              ),
            ),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 8),
              child: SizedBox(
                child: Divider(
                  color: Colors.blue,
                  thickness: 4,
                ),
              ),
            ),
            Expanded(
                child: ListView(
              children: publications,
              shrinkWrap: true,
            ))
          ],
        ),
      ),
      bottomNavigationBar: NavBar(),
    );
  }
}
