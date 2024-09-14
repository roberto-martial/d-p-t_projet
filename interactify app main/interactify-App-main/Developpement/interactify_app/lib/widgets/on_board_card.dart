import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:interactify_app/Pages/login_register_page.dart';
import 'package:interactify_app/models/on_boarding.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';

class OnBoardCard extends StatelessWidget {
  OnBoardCard({
    super.key,
    required this.onBoarding,
  });

  final OnBoarding onBoarding;

  @override
  Widget build(BuildContext context) {
    double imageWidth = MediaQuery.of(context).size.width * 0.7;
    double imageHeight = MediaQuery.of(context).size.height * 0.4;

    return Container(
      height: imageHeight,
      width: imageWidth,
      child: Column(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Padding(
            padding: const EdgeInsets.all(8),
            child: Image.asset(
              width: imageWidth,
              height: imageHeight,
              onBoarding.path,
              fit: BoxFit.contain,
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 15),
            child: Text(
              onBoarding.title,
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 22, fontWeight: FontWeight.w700),
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(top: 20),
            child: SmoothPageIndicator(
              controller: onBoarding.pageController,
              count: 3,
              effect: JumpingDotEffect(activeDotColor: Color(0xFF2EA3F8)),
            ),
          ),
          ElevatedButton(
            style: ElevatedButton.styleFrom(
                backgroundColor: Color(0xFF2EA3F8),
                fixedSize: Size(330, 50),
                shape: CircleBorder(side: BorderSide.none)),
            onPressed: () {
              Navigator.pushNamed(context, LoginRegisterPage.routeName);
            },
            child: onBoarding.pageController.hasClients
                // ? onBoarding.pageController.page == 2
                //     ? Text("Let's Start!")
                //     : FaIcon(
                //         FontAwesomeIcons.arrowRight,
                //         color: Colors.white,
                //       )
                ? Icon(
                    Icons.double_arrow_rounded,
                    color: Colors.white,
                  )
                : Icon(
                    Icons.double_arrow_rounded,
                    color: Colors.white,
                  ),
          )
        ],
      ),
    );
  }
}
