import time
import argparse

from Simulator.API.APIClient import APIClient


class MultiChunkAPIFileSender:
    @staticmethod
    def send_file(input_file, ses_id, lines_chunk):
        file_opener = open(input_file, 'r')
        lines = file_opener.readlines()
        count = 0
        lines_to_send = ""
        for line in lines:
            count += 1
            lines_to_send += line
            if count % lines_chunk == 0:
                APIClient.send_str_to_decoder(lines_to_send, ses_id)
                lines_to_send = ""
        if len(lines_to_send) > 0:
            APIClient.send_str_to_decoder(lines_to_send, ses_id)


if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument("file", help="file that you want to send")
    parser.add_argument("--id", help="identifier for session", required=False)
    parser.add_argument("--lines-per-chunk", help="number of lines per chunk", required=False, default=10)
    args = parser.parse_args()
    file = args.file
    session_id = args.id if args.id else file + "_" + str(time.time())
    lines_per_chunk = args.lines_per_chunk
    MultiChunkAPIFileSender.send_file(file, session_id, lines_per_chunk)
