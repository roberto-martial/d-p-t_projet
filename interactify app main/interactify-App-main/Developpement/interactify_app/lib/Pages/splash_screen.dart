import 'package:flutter/material.dart';
import 'package:interactify_app/Pages/home_page.dart';
import 'package:interactify_app/Pages/on_boarding_page.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SplashScreen extends StatefulWidget {
  const SplashScreen({super.key});

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  @override
  void initState() {
    super.initState();
    _navigateToHome();
  }

  _navigateToHome() async {
    await Future.delayed(Duration(seconds: 5), () {});
    Navigator.pushReplacement(
        context,
        MaterialPageRoute(
          builder: (context) => FutureBuilder<SharedPreferences>(
            future: SharedPreferences.getInstance(),
            builder: (BuildContext context, snapshot) {
              if (snapshot.hasData) {
                String? userId = snapshot.data!.getString('userId');
                if (userId != null && userId.isNotEmpty) {
                  return HomePage();
                } else {
                  return OnBoardingPage();
                }
              }else{
                return CircularProgressIndicator();
              }
            },
          ),
        ));
  }

  @override
  Widget build(BuildContext context) {
    double imageWidth = MediaQuery.of(context).size.width;
    double imageheight = MediaQuery.of(context).size.height;

    return Scaffold(
      body: Stack(
        children: <Widget>[
          // Background
          Container(
            decoration: BoxDecoration(
              gradient: RadialGradient(
                colors: [Colors.blue, Colors.white],
                radius: 1.4,
                center: Alignment(-1.5, -1.5),
              ),
            ),
          ),
          Container(
            decoration: BoxDecoration(
              gradient: RadialGradient(
                colors: [Color(0xFF2EA3F8), Colors.white],
                radius: 1.35,
                center: Alignment(1.5, 1.5),
              ),
            ),
          ),
          // Logo and text
          Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Image.asset(
                  'assets/images/logo.png',
                  fit: BoxFit.cover,
                  height: imageheight * 0.5,
                  width: imageWidth * 0.5,
                  color: Colors.transparent,
                ),
                SizedBox(height: 5),
                Text(
                  'INTERACTIFY',
                  style: TextStyle(
                    fontSize: 30,
                    fontWeight: FontWeight.bold,
                    color: Colors.black,
                  ),
                ),
                SizedBox(height: 10),
                Text(
                  'The new vision of Application',
                  style: TextStyle(
                    fontSize: 16,
                    color: Colors.grey,
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
