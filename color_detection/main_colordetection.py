from ColorDetector import ColorDetector
import time


def main():
    start_time = time.time()
    detector = ColorDetector(paths=["color_detection/images/blanc.jpg",
                                    "color_detection/images/bleu.jpg",
                                    "color_detection/images/jaune.jpg",
                                    "color_detection/images/orange.jpg",
                                    "color_detection/images/rouge.jpg",
                                    "color_detection/images/vert.jpg"],
                             show_rgb=False, show_images=False)
    detector.processImages()
    print("--- %s seconds ---" % (time.time() - start_time))
    detector.showGroups()
    return 0


if __name__ == '__main__':
    main()
