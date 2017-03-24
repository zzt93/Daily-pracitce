package jcip.taskExe;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zzt on 4/27/16.
 * <p>
 * Usage:
 */
public class HtmlRender {

    private static final Ad DEFAULT_AD = new Ad();
    private final ExecutorService service;

    public HtmlRender(ExecutorService service) {
        this.service = service;
    }

    void render(CharSequence src) {
        List<ImageInfo> infos = scanForImage(src);
        CompletionService<ImageData> completionService
                = new ExecutorCompletionService<>(service);
        for (ImageInfo info : infos) {
            completionService.submit(info::downloadData);
        }
        renderText(src);
        for (int i = 0; i < infos.size(); i++) {
            try {
                Future<ImageData> take = completionService.take();
                ImageData imageData = take.get();
                renderImage(imageData);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

    void renderTimeOut(CharSequence src) throws InterruptedException {
        long endNanos = System.nanoTime() + 100;
        Future<Ad> future = service.submit(new FetchAdTask());
        // render page body
        render(src);
        Ad ad;
        try {
            long timeLeft = endNanos - System.nanoTime();
            ad = future.get(timeLeft, TimeUnit.NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
            e.printStackTrace();
        } catch (TimeoutException e) {
            future.cancel(true);
            ad = DEFAULT_AD;
            e.printStackTrace();
        }
        // render ad
    }

    private void renderImage(ImageData imageData) {
    }

    private void renderText(CharSequence src) {
    }

    private List<ImageInfo> scanForImage(CharSequence src) {
        return null;
    }

    private static class ImageInfo {
        public ImageData downloadData() {
            return null;
        }
    }

    private static class ImageData {
    }

    private class FetchAdTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            return null;
        }
    }

    private static class Ad {
    }
}
