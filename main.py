from ColorDetector import ColorDetector
import time


def main():
    start_time = time.time()
    detector = ColorDetector(paths=["images/sample2/back.jpg", "images/sample2/down.jpg", "images/sample2/front.jpg",
                             "images/sample2/left.jpg", "images/sample2/right.jpg", "images/sample2/up.jpg"],
                             show_rgb= False, show_images= False)
    detector.processImages()
    print("--- %s seconds ---" % (time.time() - start_time))
    detector.showGroups()
    # print(detector.groups)

    return 0

if __name__ == '__main__':
    main()