public enum Direction {
        SOUTH(0),WEST(1),NORTH(2),EAST(3);
        private int frameLineNumber;

        Direction(int frameLineNumber) {
            this.frameLineNumber = frameLineNumber;
        }

        public int getFrameLineNumber() {
            return frameLineNumber;
        }
}
