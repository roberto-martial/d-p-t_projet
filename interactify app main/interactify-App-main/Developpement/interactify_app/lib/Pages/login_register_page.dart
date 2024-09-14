import 'package:flutter/material.dart';
import 'package:interactify_app/Pages/login_page.dart';
import 'package:interactify_app/Pages/register_page.dart';
import 'package:interactify_app/widgets/Button_black_border.dart';
import 'package:interactify_app/widgets/button_black.dart';

class LoginRegisterPage extends StatelessWidget {
  static const routeName = "/loginRegister";

  LoginRegisterPage({super.key});

  String registerRouteName = RegisterPage.routeName;
  String loginRouteName = LoginPage.routeName;

  @override
  Widget build(BuildContext context) {
    double imageWidth = MediaQuery.of(context).size.width * 0.6;
    double imageHeight = MediaQuery.of(context).size.height * 0.5;
    String imagePath = "assets/images/celfirst.png";
    String imageInteractify = "assets/images/logo.jpg";
    return Scaffold(
        body: Container(
      height: double.infinity,
      width: double.infinity,
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 30, horizontal: 28),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Image.asset(
              width: imageWidth,
              height: imageHeight,
              imagePath,
              fit: BoxFit.fill,
            ),
            Container(
              height: 57,
              child: ButtonBlack(
                texte: "Login",
                onPressed: () {
                  Navigator.pushNamed(context, loginRouteName);
                },
              ),
            ),
            Container(
              height: 57,
              child: ButtonBlackBorder(
                texte: "Register",
                onPressed: () {
                  Navigator.pushNamed(context, registerRouteName);
                },
              ),
            ),
          ],
        ),
      ),
    ));
  }
}
