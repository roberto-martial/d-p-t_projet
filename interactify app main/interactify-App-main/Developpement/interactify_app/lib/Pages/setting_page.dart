import 'package:flutter/material.dart';
import 'package:interactify_app/Pages/profile_page.dart';
import 'package:interactify_app/widgets/Button_black_border.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

class SettingsPage extends StatefulWidget {
  static const routeName = "/profile/settings";
  final Function(Locale) onLocaleChange;

  const SettingsPage({super.key, required this.onLocaleChange});

  @override
  State<SettingsPage> createState() => _SettingsPageState();
}

class _SettingsPageState extends State<SettingsPage> {
  bool _isDarkModeEnabled = false;
  @override
  Widget build(BuildContext context) {
    final localizations = AppLocalizations.of(context)!;

    return Scaffold(
      body: Column(
        children: [
          // Première ligne
          Container(
            color: Colors.blue,
            child: Row(
              children: [
                IconButton(
                  icon: const Icon(Icons.arrow_back, color: Colors.white),
                  onPressed: () {
                    Navigator.pushNamed(context, ProfilePage.routeName);
                  },
                ),
                Expanded(
                  child: Center(
                    child: Text(
                      localizations.profilConfiguration,
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20.0,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                ),
              ],
            ),
          ),
          // Deuxième ligne
          Container(
            color: Colors.blue,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Expanded(
                  child: Padding(
                    padding: const EdgeInsets.symmetric(vertical: 10),
                    child: Container(
                      width: 100,
                      height: 100,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        image: DecorationImage(
                          fit: BoxFit.cover,
                          image: AssetImage("images/profil.jpeg"),
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            ),
          ),
          // Troisième ligne
          Padding(
            padding: const EdgeInsets.all(15),
            child: ButtonBlackBorder(
              texte: localizations.editionProfile,
              onPressed: () {},
            ),
          ),
          // Quatrième ligne
          Container(
            color: Colors.grey[200],
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Row(
                children: [
                  Text(
                    localizations.preferences,
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  Spacer(),
                ],
              ),
            ),
          ),
          // Cinquième ligne
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Row(
              children: [
                Icon(Icons.translate),
                SizedBox(width: 16.0),
                Text(
                  localizations.langue,
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                  ),
                ),
                Spacer(),
                DropdownButton<Locale>(
                  icon: Icon(Icons.language),
                  onChanged: (Locale? newLocale) {
                    widget.onLocaleChange(newLocale!);
                  },
                  items: [
                    DropdownMenuItem(
                      value: Locale('en'),
                      child: Text(localizations.anglais),
                    ),
                    DropdownMenuItem(
                      value: Locale('fr'),
                      child: Text(localizations.francais),
                    ),
                  ],
                ),
              ],
            ),
          ),
          // Sixième ligne
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Row(
              children: [
                Icon(Icons.nightlight),
                SizedBox(width: 16.0),
                Text(
                  localizations.modeSombre,
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                  ),
                ),
                Spacer(),
                Switch(
                  value: _isDarkModeEnabled,
                  onChanged: (value) {
                    setState(() {
                      _isDarkModeEnabled = value;
                    });
                  },
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
