import 'package:flutter/material.dart';
import 'package:interactify_app/Pages/login_page.dart';
import 'package:interactify_app/widgets/button_black.dart';

class PasswordConfirmPage extends StatelessWidget {
  const PasswordConfirmPage({super.key});

  static const routeName = "/password/confirm";

  Widget build(BuildContext context) {
    String imagePath = "assets/images/Successmark.png";

    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 18),
      child: Scaffold(
        body: Padding(
            padding: const EdgeInsets.only(top: 30, bottom: 10),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Column(
                  children: [
                    Image.asset(
                      width: 120,
                      height: 120,
                      imagePath,
                      fit: BoxFit.contain,
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 20, bottom: 10),
                      child: Text(
                        "Create new password!",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                          fontSize: 30,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                    Text(
                      "Your password has been changed successfully.",
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 16,
                        color: Colors.grey,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 20),
                      child: Column(
                        children: [
                          Container(
                              height: 55,
                              child: ButtonBlack(
                                texte: "Back to Login",
                                onPressed: () {
                                  Navigator.pushNamed(
                                      context, LoginPage.routeName);
                                },
                              )),
                        ],
                      ),
                    ),
                  ],
                ),
              ],
            )),
      ),
    );
  }
}
