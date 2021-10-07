from ColorDetector import ColorDetector
import time


def main():
    start_time = time.time()
    detector = ColorDetector(paths=["images/blanc.jpg", "images/bleu.jpg", "images/jaune.jpg",
                             "images/orange.jpg", "images/rouge.jpg", "images/vert.jpg"],
                             show_rgb=False, show_images=False)
    detector.processImages()
    print("--- %s seconds ---" % (time.time() - start_time))
    detector.showGroups()
    return 0


if __name__ == '__main__':
    main()
