import 'package:flutter/material.dart';
import 'package:interactify_app/models/publication.dart';
import 'package:interactify_app/models/utilisateur.dart';
import 'package:interactify_app/services/utilisateur_service.dart';
import 'package:interactify_app/widgets/head.dart';
import 'package:interactify_app/widgets/nav_bar.dart';
import 'package:interactify_app/widgets/navigators.dart';
import 'package:interactify_app/widgets/publication_card.dart';
import 'package:interactify_app/services/publication_service.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

class HomePage extends StatefulWidget {
  static const routeName = "/home";
  PublicationService publicationService = PublicationService();
  UtilisateurService utilisateurService = UtilisateurService();
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final PublicationService _publicationService = PublicationService();
  List<Publication> publications = [];
  bool isLoading = true; // To track loading state
  String? errorMessage; // To store error message if any

  @override
  void initState() {
    super.initState();
    _loadPublications();
  }

  void _loadPublications() async {
    try {
      List<Publication> pubs = await _publicationService.getAllPub();
      setState(() {
        publications = pubs;
        isLoading = false;
      });
    } catch (e) {
      setState(() {
        errorMessage = 'Error loading publications: $e';
        isLoading = false; // Set loading to false even if there's an error
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    final localizations = AppLocalizations.of(context)!;

    return Scaffold(
      backgroundColor: Colors.white,
      appBar: const Head(),
      body: isLoading
          ? Center(child: CircularProgressIndicator()) // Show loading spinner
          : errorMessage != null
              ? Center(child: Text(errorMessage!)) // Show error message if any
              : ListView(
                  padding: const EdgeInsets.only(top: 10, bottom: 10),
                  children: [
                    const SizedBox(height: 10),
                    const Navigators(number: 0),
                    const Padding(
                      padding: EdgeInsets.only(bottom: 5),
                      child: SizedBox(
                        child: Divider(
                          color: Color.fromARGB(13, 46, 163, 248),
                          thickness: 10,
                        ),
                      ),
                    ),
                    if (publications != null && publications.isNotEmpty)
                      ...publications
                          .map((publication) =>
                              PublicationCard(publication: publication))
                          .toList()
                    else
                      Center(child: Text(localizations.noPublication)),
                  ],
                ),
      bottomNavigationBar: NavBar(),
    );
  }
}
