from ColorDetector import ColorDetector
import time


def main():
    start_time = time.time()
    detector = ColorDetector(paths=["Color detection/images/blanc.jpg",
                                    "Color detection/images/bleu.jpg",
                                    "Color detection/images/jaune.jpg",
                                    "Color detection/images/orange.jpg",
                                    "Color detection/images/rouge.jpg",
                                    "Color detection/images/vert.jpg"],
                             show_rgb=False, show_images=False)
    detector.processImages()
    print("--- %s seconds ---" % (time.time() - start_time))
    detector.showGroups()
    return 0


if __name__ == '__main__':
    main()
